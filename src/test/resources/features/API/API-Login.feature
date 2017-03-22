# Created by sumadragos at 22/03/17
Feature: #Enter feature name here
  # Enter feature description here


  Background:
    Given baseUri is "http://10.20.35.100/"

  Scenario: As a user I want to login into the MINT Platform

    Given I am a user identified by
      | username   | testCucumber@cucumber.com |
      | credential | uxp4life                  |
    When the client performs POST request on "/platform/rest/v40/session/start"
    Then status code is 200
    And response is not empty

  Scenario: As a user I want to login into the MINT Platform using legacy authentication
    Given I am a user identified by
      | username   | testCucumber@cucumber.com |
      | credential | uxp4life                  |
    When the client performs POST request on "/platform/rest/v40/session/start"
    Then status code is 200
    And response is not empty

  Scenario: As a user I want to validate the login call responses
    Given I am a user identified by username <username> and password <credential>
    When the client performs POST request on "/platform/rest/v40/session/start"
    Then status code is <statusCode>
    And response contains <errorMsg>

  Examples:
  |username|credential|statusCode|errorMsg|
  |testCucumber@cucumber.com|uxp4life|200||
  |testCucumber@cucumber.com|uxp4life2|403|invalid credentials|