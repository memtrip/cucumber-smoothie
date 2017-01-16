package com.memtrip.cucumber.smoothie;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Log {
    private static Log instance;

    private Messager messager;

    public static void warning(String message) {
        if (instance != null) {
            instance.messager.printMessage(Diagnostic.Kind.WARNING, message);
        }
    }

    public static void error(String message) {
        if (instance != null) {
            instance.messager.printMessage(Diagnostic.Kind.ERROR, message);
        }
    }

    public static void note(String message) {
        if (instance != null) {
            instance.messager.printMessage(Diagnostic.Kind.NOTE, message);
        }
    }

    static Log createInstance(Messager messager) {
        instance = new Log(messager);
        return instance;
    }

    private Log(Messager messager) {
        this.messager = messager;
    }
}
