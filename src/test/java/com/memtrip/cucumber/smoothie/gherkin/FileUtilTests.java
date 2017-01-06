package com.memtrip.cucumber.smoothie.gherkin;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class FileUtilTests {

    @Test
    public void loadTestResource() {

        // given
        FileUtil fileUtil = new FileUtil();

        // when
        String contents = fileUtil.getFile("cow.feature");

        // then
        assertNotNull(contents);
    }
}
