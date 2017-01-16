package com.memtrip.cucumber.smoothie.gherkin;

import com.memtrip.cucumber.smoothie.Log;
import gherkin.Parser;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;

import java.util.List;

class GherkinParser {
    private FileUtil fileUtil;
    private Compiler compiler;
    private Parser<GherkinDocument> parser;

    GherkinParser(FileUtil fileUtil, Compiler compiler, Parser<GherkinDocument> parser) {
        this.fileUtil = fileUtil;
        this.compiler = compiler;
        this.parser = parser;
    }

    List<Pickle> getPickles(String projectRootFolderPath, String fileName) {
        String file = fileUtil.getFile(projectRootFolderPath, fileName);
        Log.warning("File: " + file);
        GherkinDocument gherkinDocument = parser.parse(file);
        return compiler.compile(gherkinDocument, "");
    }
}