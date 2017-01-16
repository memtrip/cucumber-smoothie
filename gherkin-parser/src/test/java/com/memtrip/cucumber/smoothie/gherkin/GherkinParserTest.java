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

import gherkin.Parser;
import gherkin.ast.GherkinDocument;
import gherkin.pickles.Compiler;
import gherkin.pickles.Pickle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GherkinParserTest {

    @Test
    public void getPickles() {

        // given
        FileUtil fileUtil = mock(FileUtil.class);
        when(fileUtil.getFile("gherkin-parser","cow.feature")).thenReturn("file_contents");

        GherkinDocument gherkinDocument = mock(GherkinDocument.class);
        Parser parser = mock(Parser.class);
        when(parser.parse("file_contents")).thenReturn(gherkinDocument);

        List<Pickle> pickleOutput = new ArrayList<>();
        Compiler compiler = mock(Compiler.class);
        when(compiler.compile(gherkinDocument, "")).thenReturn(pickleOutput);

        GherkinParser gherkinParser = new GherkinParser(fileUtil, compiler, parser);

        // when
        List<Pickle> pickles = gherkinParser.getPickles("gherkin-parser", "cow.feature");
        assertNotNull(pickles);
    }
}
