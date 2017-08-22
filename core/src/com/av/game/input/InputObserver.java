package com.av.game.input;

import java.util.LinkedList;

public class InputObserver implements InputHandler {
    private static InputObserver instance;

    private LinkedList<InputHandler> handlers;

    private InputObserver() {
        handlers = new LinkedList<InputHandler>();
    }

    public static void createInstance() {
        instance = new InputObserver();
    }

    public static void addInputListenner(InputHandler handler) {
        instance.handlers.add(handler);
    }

    public static void clear() {
        instance.handlers.clear();
    }

    public static InputObserver getInstance() {
        return instance;
    }

    @Override
    public void handleInput() {
        for(InputHandler handler : handlers)
            handler.handleInput();
    }
}
