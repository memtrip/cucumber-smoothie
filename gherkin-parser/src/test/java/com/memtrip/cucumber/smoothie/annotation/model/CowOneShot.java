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
package com.memtrip.cucumber.smoothie.annotation.model;

import com.memtrip.cucumber.smoothie.spec.*;

import java.util.Date;

@Feature(
        projectRootFolderName="gherkin-parser",
        featureFilePath="src/test/resources/cow.feature",
        oneShot = true
)
public class CowOneShot {

    @Background
    public static class cow_setup {

        @Given("a cow that was born on $dob is called $name")
        public void a_cow_that_was_born_on_dob_is_called_name(Date dob, String name) {

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

    @Scenario("a cow is ready to be milked")
    public static class cow_is_ready_to_be_milked {

        @Given("the cow weighs more than $weight kg")
        public void the_cow_weighs_more_than_kg(double weight) {
            // test code
        }

        @And("the cow is more than $y years $m months and $d days old")
        public void the_cow_is_of_age(int years, int months, int days) {
            // test code
        }

        @When("the date is $milking_date")
        public void the_date_is_milking_date(Date milkingDate) {
            // test code
        }

        @Then("the cow should be milked")
        public void the_cow_should_be_milked() {
            // test code
        }
    }
}