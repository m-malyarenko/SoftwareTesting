Feature: Ex1

Background:
    Given Web page is opened
    And User is logged in as "Roman" with password "Jdi1234"

Scenario: Test bowser title
    Then Browser has a proper title

Scenario: Test user logging
    Then User name is "ROMAN IOVLEV"

Scenario: Test page header
    Then There are 4 items in the header
    And Header items have proper names

Scenario: Test benefit images on the Home Page
    When Focus is on the Home Page
    Then There are 4 benefit images
    And Benefit images has proper captions
    And Home page has proper header
    And Home page has proper text

Scenario: Test central Iframe
    When Focus is on the Home Page
    Then Central iframe is displayed
    And EPAM logo is displayed
    And Focus is switched to the Home Page

Scenario: Test sub header of the Home Page
    When Focus is on the Home Page
    Then There is proper text in a sub header
    And Sub header has proper link

Scenario: Test side sections of the Home Page
    When Focus is on the Home Page
    Then Home Page has left section and footer