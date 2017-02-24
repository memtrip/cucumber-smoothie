@acceptance
Feature: Cow

    Background:
        Given a cow that was born on 2016-12-01 is called "Nelly Newton"

    @android @ios @offline
    Scenario Outline: feeding a suckler cow
        Given the cow weighs <weight> kg
        When we calculate the feeding requirements
        Then the energy should be <energy> MJ
        And the protein should be <protein> kg
        And the cow alive is true
        And the cow initial is 'S'

        Examples:
            | weight  | energy              | protein |
            |    4.5  |  265000000000000000 |     215 |
            |    5.0  |  295000000000000000 |     245 |
            |    57.5 |  315000000000000000 |     255 |
            |    6.31 |  370000000000000000 |     305 |

    @backend
    Scenario: a cow is ready to be milked
        Given the cow weighs more than 150 kg
        And the cow is more than 1 years 6 months and 31 days old
        When the date is 2016-10-22
        Then the cow should be milked