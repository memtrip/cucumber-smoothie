/**
 * Copyright 2013-present memtrip LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.memtrip.cucumber.smoothie;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Log {
    private static Log instance;

    private Messager messager;

    public static void note(String message) {
        if (instance != null) {
            instance.messager.printMessage(Diagnostic.Kind.NOTE, message);
        }
    }

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

    static Log createInstance(Messager messager) {
        instance = new Log(messager);
        return instance;
    }

    private Log(Messager messager) {
        this.messager = messager;
    }
}
