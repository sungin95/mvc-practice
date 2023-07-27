package org.example.mvc.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // key: /users [value] UserController
    private Map<String, Controller> mappings = new HashMap<>();

    public void init() {
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath) {
        return mappings.get(uriPath);
    }
}
