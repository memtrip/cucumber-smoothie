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
        Log.warning("Generating test suite...");
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
