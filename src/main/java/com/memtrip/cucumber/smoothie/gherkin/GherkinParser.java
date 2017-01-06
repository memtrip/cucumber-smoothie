package com.memtrip.cucumber.smoothie.gherkin;

import gherkin.Parser;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import java.util.List;

public class GherkinParser {
    private Compiler compiler;
    private Parser<GherkinDocument> parser;

    public GherkinParser(Compiler compiler, Parser<GherkinDocument> parser) {
        this.compiler = compiler;
        this.parser = parser;
    }

    List<Pickle> getPickles(String file) {
        GherkinDocument gherkinDocument = parser.parse(file);
        return compiler.compile(gherkinDocument, "");
    }
}