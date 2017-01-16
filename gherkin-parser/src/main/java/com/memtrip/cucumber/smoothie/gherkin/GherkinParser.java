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
        Log.note("File: " + file);
        GherkinDocument gherkinDocument = parser.parse(file);
        return compiler.compile(gherkinDocument, "");
    }
}