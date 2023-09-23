@Submit_Module
Feature: CURD Operations for Assignment Submit Module

  @Assignment_Submit_TC_004
  Scenario: Check if user able to retrieve all submission with valid LMS API for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint for Assignment Submit Module
    When User sends HTTPS Request for getting all submissions
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_001
  Scenario Outline: Check if user able to  create a submission  with valid endpoint and request body in Assignment Submit Module(non existing values)
    Given User creates POST Request for the LMS API Assignment Submit Module
    When User sends HTTPS Request and  request Body for Assignment Submit Module from excel "<Sheetname>" and <RowNumber>
    Then User receives 201 Created Status with response body for Assignment Submit Module.

    Examples: 
      | Sheetname                | RowNumber |
      | Assignment_Submit_Module |         1 |

  @Assignment_Submit_TC_002
  Scenario Outline: Check if user able to  create a submission  with valid endpoint and request body in Assignment Submit Module(non existing values)
    Given User creates POST Request for the LMS API Assignment Submit Module
    When User sends HTTPS Request and  request Body for Assignment Submit Module from excel "<Sheetname>" and <RowNumber>
    Then User receives 400 Bad Request Status with message and boolean success details for Assignment Submit Module

    Examples: 
      | Sheetname                | RowNumber |
      | Assignment_Submit_Module |         1 |

  @Assignment_Submit_TC_003
  Scenario: Check if user able to create a submission missing mandatory fields in request body
    Given User creates POST Request for the LMS API endpoint with missing field
    When User sends HTTPS Request and request Body for Assignment Submit Module
    Then User receives 400 Bad Request Status with message and boolean success details for Assignment Submit Module

  @Assignment_Submit_TC_006
  Scenario: Check if user able to retrieve a grades with invalid Assignment ID for assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with invalid Assignment ID Submit
    When User sends HTTPS Request with invalid Assignment ID
    Then User receives 404 Not Found Status with message and boolean success details for Assignment Submit Module.

  @Assignment_Submit_TC_005
  Scenario: Check if user able to retrieve a grades with valid Assignment ID for assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with valid Assignment ID Assignment Submit Module
    When User sends HTTPS Request with valid Assignment ID
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_014
  Scenario: Check if user able to retrieve a submission with invalid User Id Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with invalid user ID for Assignment Submit Module
    When User sends HTTPS Request submission with Invalid User ID to LMS API
    Then User receives 404 Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_013
  Scenario: Check if user able to retrieve a submission with valid User ID for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with valid User Id for Assignment Submit Module
    When User sends HTTPS Request with valid User Id for Assignment Submit Module
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_008
  Scenario: Check if user able to retrieve a grades with invalid Student ID for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with invalid Student ID
    When User sends HTTPS Request with invalid Student ID for Assignment Submit Module
    Then User receives 404 Not Found Status with message and boolean success details for Assignment Submit Module.

  @Assignment_Submit_TC_007
  Scenario: Check if user able to retrieve a grades with valid Student ID for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with valid Student Id
    When User sends HTTPS Request with valid Student ID for Assignment Submit Module
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_010
  Scenario: Check if user able to retrieve a grades with invalid Batch ID for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with invalid Batch Id
    When User sends HTTPS Request with invalid Batch Id for Assignment Submit Module
    Then User receives 404 Not Found Status with message and boolean success details for Assignment Submit Module.

  @Assignment_Submit_TC_009
  Scenario: Check if user able to retrieve a grades with valid  batch ID Check for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with valid Batch Id
    When User sends HTTPS Request with valid Batch Id for Assignment Submit Module
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_012
  Scenario: Check if user able to retrieve a submission with invalid Batch Id for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint with submisssion invalid Batch  Id
    When User sends HTTPS Request with submission invalid Batch Id for Assignment Submit Module
    Then User receives 404 Not Found Status with message and boolean success details for submission invalid Assignment Submit Module.

  @Assignment_Submit_TC_011
  Scenario: Check if user able to retrieve a submission with valid batch ID for Assignment Submit Module
    Given User creates GET Request for the LMS API endpoint submission with valid Batch Id
    When User sends HTTPS Request with submission valid Batch Id for Assignment Submit Module
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_016
  Scenario: Check if user able to update a submission with invalid submission ID and mandatory request body
    Given User creates PUT Request for the LMS API endpoint  and  invalid submission ID
    When User sends HTTPS Request  and  request Body  with mandatory, additional  fields
    Then User receives 404 Not Found Status with message and boolean success details Submit

  @Assignment_Submit_TC_017
  Scenario: Check if user able to update a submission with invalid submission ID and mandatory request body
    Given User creates PUT Request for the LMS API endpoint  and  invalid submission ID
    When User sends HTTPS Request  and request Body  (missing mandatory fields)
    Then User receives 400 Bad Request Status with message and boolean success details Submit

  @Assignment_Submit_TC_015
  Scenario: Check if user able to update a submission with valid  submission ID and mandatory request body
    Given User creates PUT Request for the LMS API endpoint  and Valid submission Id
    When User sends HTTPS Request and  request Body with valid info for mandatory ,additional  fields
    Then User receives 200 OK Status with response body for Assignment Submit Module.

  @Assignment_Submit_TC_019
  Scenario: Check if user able to  grade assignment with invalid submission  Id and mandatory request body
    Given User creates PUT Request for the LMS API endpoint  and  invalid submission ID
    When User sends HTTPS Request and  request Body with mandatory  fields
    Then User receives 404 Not Found Status with message and boolean success details Submit

  @Assignment_Submit_TC_020
  Scenario: Check if user able to  grade assignment with valid submission  Id and missing mandatory request body
    Given User creates PUT Request for the LMS API endpoint  and Valid submission Id
    When User sends HTTPS Request  and request Body  (missing mandatory fields)
    Then User receives 400 Bad Request Status with message and boolean success details Submit

  @Assignment_Submit_TC_018
  Scenario: Check if user able to  grade assignment with valid submission  Id and mandatory request body
    Given User creates PUT Request for Grade assignment the LMS API endpoint  and Valid submission Id
    When User sends HTTPS Request and  request Body with mandatory  fields
    Then User receives 200 OK Status with updated value in response body with valid submission id

  @Assignment_Submit_TC_022
  Scenario: Check if user able to delete a submission with valid LMS API,invalid Submission Id for Assignment Submit module
    Given User creates DELETE Request for the LMS API endpoint  and  invalid submission id
    When User sends HTTPS Request with invalid submissionID
    Then User receives 404 Not Found Status with message and boolean success details for Assignment Submit Module.

  @Assignment_Submit_TC_021
  Scenario: Check if user able to delete a submission with valid submission Id for Assignment Submit Module
    Given User creates DELETE Request for the LMS API endpoint  and  valid submission ID
    When User sends HTTPS Request with valid submissionID
    Then User receives 200 Ok status with message Submit
