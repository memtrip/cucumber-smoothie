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
package com.memtrip.cucumber.smoothie.freemarker;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.memtrip.cucumber.smoothie.Log;
import com.memtrip.cucumber.smoothie.gherkin.model.FeatureGherkin;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Source {
    private FreeMarker freeMarker;
    private Filer filer;

    public Source(Filer filer) throws IOException {
        this.filer = filer;
        freeMarker = new FreeMarker();
    }

    public String generate(List<FeatureGherkin> featureGherkins) {
        Log.note("Generating test suite...");
        return freeMarker.generate("cucumber.freemarker", new DataSource(featureGherkins));
    }

    public String format(String source) {
        try {
            return new Formatter().formatSource(source);
        } catch (FormatterException e) {
            return null;
        }
    }

    public boolean save(String packageName, String name, String sourceCode) {
        String nameWithPackage = packageName + "." + name;

        try {
            JavaFileObject jfo = filer.createSourceFile(nameWithPackage);
            Writer writer = jfo.openWriter();
            writer.append(sourceCode);
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
