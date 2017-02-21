package com.memtrip.cucumber.smoothie.spec;

public @interface Feature {

    /**
     * @return the name of the projects root folder, this is used to resolve the feature file relative paths.
     */
    String projectRootFolderName() default "";

    /**
     * @return the path to the feature file, relative to projectRootFolderName.
     */
    String featureFilePath() default "";

    /**
     * @return true if the feature runs all Scenarios in a single junit test.
     */
    boolean oneShot() default false;
}