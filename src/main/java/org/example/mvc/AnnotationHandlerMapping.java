package org.example.mvc;

import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.RequestMapping;
import org.example.mvc.controller.RequestMethod;
import org.example.mvc.view.HandlerMapping;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() { // 학습 권장
        Reflections reflections = new Reflections(basePackage);

        // HomeController
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);

        clazzesWithControllerAnnotation.forEach(clazz ->
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> {
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class); //RequestMapping 모두 축출

                    // @RequestMapping(value = "/", method = RequestMethod.GET)
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethod)
                            ));
                })
        );
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
