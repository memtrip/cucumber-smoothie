package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.spec.*;

import java.util.Date;

@Feature(
        projectRootFolderName="gherkin-parser",
        featureFilePath="src/test/resources/cow.feature"
)
public class Cow {

    @Background
    public static class cow_setup {

        @Given("a cow that was born on $dob is called $name")
        public void a_cow_that_was_born_on_dob_is_called_name(Date dob, String name) {
            // run any setup here
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