package com.memtrip.cucumber;

import com.memtrip.cucumber.annotations.*;

@Feature("coffee.feature")
class ServeCoffee {

    @Scenario("Buy last coffee")
    static class Buy_last_coffee {

        @Given("There are %d coffees left in the machine")
        public void There_are_coffees_left_in_the_machine(int numberOfCoffees) {
            // code
        }

        @And("I have deposited %d")
        public void I_have_deposited() {
            // code
        }

        @When("I press the coffee button")
        public void I_press_the_coffee_button() {
            //
        }

        @Then("I should be served a coffee")
        public void I_should_be_served_a_coffee() {
            //
        }
    }

    @Scenario("Buy first coffee")
    static class Buy_first_coffee {

        @Given("There are %d coffees left in the machine")
        public void There_are_coffees_left_in_the_machine(int numberOfCoffees) {
            // code
        }

        @And("I have deposited %d")
        public void I_have_deposited() {
            // code
        }

        @When("I press the coffee button")
        public void I_press_the_coffee_button() {
            //
        }

        @Then("I should be served a coffee")
        public void I_should_be_served_a_coffee() {
            //
        }
    }
}