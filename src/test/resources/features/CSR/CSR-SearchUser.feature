# Created by sumadragos at 20/03/17
Feature: CSR Search user
  # Login into CSR and search for a user

  Background:
    Given baseUri is "http://10.20.35.101:8091/csr/"

  Scenario: CSR Login
    Given queryParam "username" is "adminPL"
    And queryParam "password" is "1111"
    And value assertions are case-insensitive
    And content type is "application/x-www-form-urlencoded"
    And header <X-Forwarded-Host> with value "upc.pl"
    When the client performs POST request on "/console/doLogin"
    Then status code is 302
    
  Scenario: CSR Login and register user

    #Login in CSR
    Given queryParam "username" is "adminPL"
    And queryParam "password" is "1111"
    And value assertions are case-insensitive
    And content type is "application/x-www-form-urlencoded"
    And header <X-Forwarded-Host> with value "upc.pl"
    When the client performs POST request on "/console/doLogin"
    Then status code is 302

    #Start the registration process
    And I pass the MINTsession to the next request
    When the client performs POST request on "rest/v40/process/start/com.uxpsystems.mint.libertyglobal.process.AccountTokenEnrolmentProcessOwner"
    Then  status code is 200
    And response contains property "displayMessage" matching pattern "Provide Customer Information"

    #Provide user details
    Given I provide the following user details
      |countryCode|PL|
      |custId|1234567|
      |email|testCucumber@cucumber.com|
    And content type is "application/json"
    And header <X-Forwarded-Host> with value "upc.pl"
    And I use the session from the previous request
    When the client performs PUT request on "/rest/v40/process/step"
    Then status code is 200
    And response contains property "lastStep" matching pattern "true"

    #Complete registration




