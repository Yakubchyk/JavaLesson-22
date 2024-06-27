package org.example;


import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) {

        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
            httpServer.createContext("/hello", new HelloHttpHandler());
            httpServer.createContext("/calc", new CalculatorHttpHandler());

            httpServer.setExecutor(null);
            httpServer.start();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
