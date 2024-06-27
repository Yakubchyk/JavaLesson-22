package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CalculatorHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        Map<String, String> stringStringMap = parseQuery(query);
        int num1 = Integer.parseInt(stringStringMap.get("num1"));
        int num2 = Integer.parseInt(stringStringMap.get("num2"));
        String type = stringStringMap.get("type");
        int result = switch (type) {
            case "sum":
                yield num1 + num2;

            case "sub":
                yield num1 - num2;
            case "mul":
                yield num1 * num2;
            case "div":
                yield num1 / num2;
            case "mod":
                yield num1 % num2;

            default:
                throw new IllegalStateException("Unexpected value: " + type);
        };
        String resultString = "<h1>Result: %s</h1>".formatted(result);
        exchange.sendResponseHeaders(200, resultString.length());
        exchange.getResponseBody().write(resultString.getBytes());
        exchange.close();
    }


private Map<String, String> parseQuery(String query) {
    Map<String, String> result = new HashMap<>();
    String[] split = query.split("&");
    for (String s : split) {
        String[] keyValue = s.split("=");
        result.put(keyValue[0], keyValue[1]);

    }
    return result;
}
}

