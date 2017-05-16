Cucumber Smoothie
======================

![alt text](http://oi63.tinypic.com/zkobjq.jpg "Cucumber smoothie")

Cucumber Smoothie is an alternative to cucumber-jvm that is designed specifically for the Android instrumentation framework. Step definitions are defined in the same way as cucumber-jvm, the annotations are preprocessed to generate a CucumberRunner class that contains a series of tests that execute the step definitions.

### Gradle dependencies
```groovy
dependencies {
    androidTestCompile 'com.memtrip.cucumber.smoothie:gherkin-binding:1.0.3'
    androidTestAnnotationProcessor 'com.memtrip.cucumber.smoothie:gherkin-parser:1.0.3'
}
```

### Define a Gherkin feature file
```
Feature: Cow

    Background:
        Given a cow that was born on 2016-12-01 is called "Nelly Newton"

    Scenario Outline: feeding a suckler cow
        Given the cow weighs <weight> kg
        When we calculate the feeding requirements
        Then the energy should be <energy> MJ
        And the protein should be <protein> kg
        And the cow alive is true
        And the cow initial is 'S'

        Examples:
            | weight  | energy | protein |
            |    4.5  |  265   |     215 |
            |    5.0  |  295   |     245 |
            |    57.5 |  315   |     255 |
            |    6.31 |  370   |     305 |
```

### Bind the Gherkin behaviour to step definitions
The step definitions are bound in the same way as cucumber-jvm. However, the Feature annotation must supply the name of the projects root folder and the relative path to the feature file. *These values are used internally by the preprocessor to work out the absolute path of the .feature file*

```java
@Feature(
        projectRootFolderName="gherkin-parser",
        featureFilePath="src/test/resources/cow.feature"
)
public class Cow {

    @Background
    public static class cow_setup {

        @Given("a cow that was born on $dob is called $name")
        public void a_cow_that_was_born_on_dob_is_called_name(Date dob, String name) {
            // test setup
        }
    }

    @Scenario("feeding a suckler cow")
    public static class feeding_a_suckler_cow {

        @Given("the cow weighs <weight> kg")
        public void the_cow_weighs_x_kg(double weight) {
            // test code
        }

        @When("we calculate the feeding requirements")
        public void we_calculate_the_feeding_requirements() {
            // test code
        }

        @Then("the energy should be <energy> MJ")
        public void the_energy_should_be_x_MJ(long energy) {
            // test code
        }

        @And("the protein should be <protein> kg")
        public void the_protein_should_be_x_kg(int protein) {
            // test code
        }

        @And("the cow alive is $alive")
        public void the_cow_is_alive(boolean alive) {
            // test code
        }

        @And("the cow initial is $initial")
        public void the_cow_initial_is(char initial) {
            // test code
        }
    }
}
```

### Usage
The annotation preprocessor will generate a class at `com.memtrip.cucumber.smoothie.CucumberRunner`. To run the step definitions as tests, a child of CucumberRunner must be created and annotated with `@RunWith(AndroidJUnit4.class)`

```
import com.memtrip.cucumber.smoothie.CucumberRunner;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MyCucumberRunner extends CucumberRunner {
    // this will run all the step definitions that are bound to .feature files
}
```

### Tags
Cucumber tags are supported.

```
Feature: Cow

    Background:
        Given a cow that was born on 2016-12-01 is called "Nelly Newton"

    @acceptance
    Scenario: a cow is ready to be milked
        Given the cow weighs more than 150 kg
        And the cow is more than 1 years 6 months and 31 days old
        When the date is 2016-10-22
        Then the cow should be milked
```

Seperate Runner classes are generated per tag in the format of; {tagName}Runner, a child of these runner classes must be created to run Tag specific tests.
```
import com.memtrip.cucumber.smoothie.AcceptanceRunner;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MyAcceptanceRunner extends AcceptanceRunner {
    // this will run the step definitions that are tagged with @acceptance in the feature file
}
```

### Gherkin reference
- https://cucumber.io/docs/reference

### TODO
- List arguments
- Match with regular expressions
- Validation: At least one scenario annotation is required per feature annotation
- Validation: At least one behaviour annotation is required per scenario annotation
- Validation: The arguments of the Gherkin pickles match those found in the behaviour annotations
