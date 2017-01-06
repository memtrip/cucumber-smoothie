package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.spec.*;

import java.util.Date;

@Feature("cow.feature")
class Cow {

//    @Background
//    static class background {
//
//        @Given("a $100 microwave was sold on 2015-11-03")
//        public void a_microwave_was_sold(double price, Date date) {
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