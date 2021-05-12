Feature: Ex2

Background:
    Given Web page is opened
    And User is logged in as "Roman" with password "Jdi1234"

Scenario: Test bowser title
    Then Browser has a proper title

Scenario: Test user logging
    Then User name is "ROMAN IOVLEV"

Scenario: Test Service menu
    When Focus is on the Home Page
    And Header Service menu is opened
    Then Header Service menu has 9 options
    And Header Service menu has proper options
    When Left side Service menu is opened
    Then Left side Service menu has 9 options
    And Left side Service menu has proper options

Scenario: Test elements on the Different Elements Page
    Given Different Elements page is opened
    Then There are 4 Check Boxes
    And There are 4 Radio Buttons
    And There are Select Box
    And There are Buttons

Scenario: Test side sections on the Different Elements Page
    When Different Elements page is opened
    Then Different Elements page has left and right sections

Scenario: Test if Check Boxes are working and there is log information
    When Different Elements page is opened
    And Check Boxes: "Water, Wind" are clicked
    Then Check Boxes: "Water, Wind" are selected and there is log info
    When Check Boxes: "Water, Wind" are clicked
    Then Check Boxes: "Water, Wind" are unselected and there is log info

Scenario: Test if Radio Buttons are working and there is log information
    When Different Elements page is opened
    And Radio Button "Selen" is clicked
    Then Radio Button "Selen" is selcted and there is log info

Scenario: Test if Select Box is working and there is log info
    When Different Elements page is opened
    And Select Box option "Yellow" is clicked
    Then Select Box option "Yellow" is selected and there is log info


