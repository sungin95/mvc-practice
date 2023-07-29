package org.example.mvc;

import org.example.mvc.HandlerKey;


public interface HandlerMapping {
    Object findHandler(HandlerKey handlerKey);
}
