package org.example.mvc.view;

import org.example.mvc.HandlerKey;


public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
