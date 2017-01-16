package com.memtrip.cucumber.smoothie.spec;

public @interface Feature {
    String projectRootFolderName() default "";
    String featureFilePath() default "";
}