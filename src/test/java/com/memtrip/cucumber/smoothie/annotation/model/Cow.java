package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.spec.*;

import java.util.Date;

@Feature("cow.feature")
class Cow {

//    @Background
//    static class background {
//
//        @Given("a cow that was born on 2016-12-01 and has a name of \"Nelly Newton\"")
//        public void a_cow_that_was_born_on_dob_and_has_a_name_of_name(Date dob, String name) {
//            // run any setup here
//        }
//    }

    @Scenario("feeding a suckler cow")
    static class feeding_a_suckler_cow {

        @Given("the cow weighs <weight> kg")
        public void the_cow_weighs_x_kg(int weight) {
            // code
        }

        @When("we calculate the feeding requirements")
        public void we_calculate_the_feeding_requirements() {
            // code
        }

        @Then("the energy should be <energy> MJ")
        public void the_energy_should_be_x_MJ(int energy) {
            //
        }

        @And("the protein should be <protein> kg")
        public void the_protein_should_be_x_kg(int protein) {
            //
        }
    }
}