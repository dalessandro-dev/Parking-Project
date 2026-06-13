package br.com.dalessandro.parkingproject.controllers;

import br.com.dalessandro.parkingproject.dtos.ErrorResponseDTO;
import br.com.dalessandro.parkingproject.utils.JacksonJsonConversorUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

interface IController extends HttpHandler {
    default boolean conferExactUrlAndMethod(HttpExchange exchange, String method, String uri) throws IOException {
        String path = exchange.getRequestURI().getPath();

        return exchange.getRequestMethod().equalsIgnoreCase(method) && path.equals(uri);
    }

    default boolean conferUrlWithIdAndMethod(HttpExchange exchange, String method, String uri) throws IOException {
        String path = exchange.getRequestURI().getPath();

        return exchange.getRequestMethod().equalsIgnoreCase(method) && path.startsWith(uri) && path.length() > uri.length();
    }

    default String extractUrlId(HttpExchange exchange, String rotaBase) {
        String path = exchange.getRequestURI().getPath();

        try {
            return path.replace(rotaBase, "").replace("/", "");
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid ID or missing from url");
        }
    }

    default void sendErrorResponse(HttpExchange exchange, String message, int status) throws IOException {
        ErrorResponseDTO errorDTO = new ErrorResponseDTO(message);

        String jsonResponse = JacksonJsonConversorUtils.convertObjectToJson(errorDTO);
        byte[] responseBytes = jsonResponse.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, responseBytes.length);

        try (java.io.OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}
