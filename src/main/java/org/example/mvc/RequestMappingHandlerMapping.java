package org.example.mvc;

import org.example.mvc.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // key: /users [value] UserController
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    public void init() {
        mappings.put(new HandlerKey(RequestMethod.GET,"/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST,"/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET,"/user/form"), new ForwardController("/user/form")); // 바로 이동, 보통은 처리하고 이동
    }

    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }
}
