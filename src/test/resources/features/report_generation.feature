@smoke
Feature: Employee and User Integration Report

  Scenario: Verify report table equals expected merged data
    Given I open the local integration report
    Then the table should match the expected merged CSV
