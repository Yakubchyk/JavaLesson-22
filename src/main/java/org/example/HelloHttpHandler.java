package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        String[] split = query.split("=");

        String s = "<h1>Hello %s!</h1>".formatted(split[1]);
        try {
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(s.getBytes());
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
