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
