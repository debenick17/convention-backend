package org.afecam.convention.handler;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import org.afecam.convention.responses.MediaTypes;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResourceNotFoundHandler implements Handler<RoutingContext> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ResourceNotFoundHandler.class);

    @Override
    public void handle(RoutingContext event) {
        LOGGER.debug("Resource Not Found {}",
                event.request()
                        .absoluteURI());

        JsonObject response = new JsonObject();
        JsonArray errors = new JsonArray();
        JsonObject error = new JsonObject();
        error.put("message", "Resource Not Found");
        error.put("timestamp", LocalDateTime.now()
                .format(DateTimeFormatter.ISO_DATE_TIME));
        errors.add(error);
        response.put("errors", errors);

        event.response()
                .setStatusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .putHeader(HttpHeaders.CONTENT_TYPE, MediaTypes.APPLICATION_JSON)
                .end(response.encode());
    }
}