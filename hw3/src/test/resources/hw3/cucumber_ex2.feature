Feature: Ex2

Background:
    Given Web page is opened
    And User is logged in as "Roman" with password "Jdi1234"

Scenario: Test bowser title
    Then Browser title is "Home Page"

Scenario: Test user logging
    Then User name is "ROMAN IOVLEV"

Scenario: Test Service menu
    When Focus is switched to the Home Page
    Then Header Service menu has 9 options
    And Header Service menu has proper options
    And Left side Service menu has 9 options
    And Left side Service menu has proper options

Scenario: Test elements on the Different Elements Page
    Given Different Elements page is opened
    Then There are 4 Check Boxes
    And There are 4 Radio Buttons
    And There are Select Box
    And There are Buttons

Scenario: Test side sections on the Different Elements Page
    Then Different Elements page has left and right sides

Scenario: Test if Check Boxes are working and there is log information
    When Check Boxes: "Water, Wind" are clicked
    Then Check Boxes: "Water, Wind" are selected and there is log info
    When Check Boxes: "Water, Wind" are clicked
    Then Check Boxes: "Water, Wind" are unselected and there is log info

Scenario: Test if Radio Buttons are working and there is log information
    When Radio Button "Selen" is clicked
    Then Radio Button "Selen" is selcted and there is log info

Scenario: Test if Select Box is working and there is log info
    When Select Box option "Yellow" is clicked
    Then Select Box option "Yellow" is selected and there is log info