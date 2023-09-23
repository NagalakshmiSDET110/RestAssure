@AssignmentModule
Feature: CURD Operations for Assignment Module

	@Assignment_TC_001
  Scenario: Check if user able to add a record with valid endpoint and request body using dynamic variables for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module through dynamic variables
    Then  User receives 201 Created Status with response body for Assignment Module.

  @Assignment_TC_002
  Scenario Outline: Check if user able to add a record with valid endpoint and request body in Assignment module (non existing values)
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 201 Created Status with response body for Assignment Module.
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         1 |

  @Assignment_TC_003
  Scenario Outline: Check if user able to add a record with valid endpoint and request body in Assignment module (existing values)
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 400 Bad Request Status with message and boolean success details for already existing assignment name
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         2 |
      
  @Assignment_TC_004
  Scenario Outline: Check if user able to add a record missing assignment name in request body for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 400 Bad Request Status with message and boolean success details for missing fields - Assignment Name
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         3 |
 
   @Assignment_TC_005
  Scenario Outline: Check if user able to add a record missing mandatory assignment description in request body for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 400 Bad Request Status with message and boolean success details for missing fields - Assignment Description
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         4 |
      
   @Assignment_TC_006
  Scenario Outline: Check if user able to add a record with invalid fields in request body for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 500 Internal Server Error with message and boolean success details for invalid batch id field
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         5 |
      
   @Assignment_TC_007
  Scenario Outline: Check if user able to add a record with invalid fields in request body for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 404 Not Found Status with message and boolean success details for invalid created by field
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |        6  |
      
   @Assignment_TC_008
  Scenario Outline: Check if user able to add a record with invalid fields in request body for Assignment Module
    Given User creates POST Request for the LMS API Assignment Module
    When  User sends HTTPS Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 404 Not Found Status with message and boolean success details for invalid grader id field
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |        7  |
      
  @Assignment_TC_009
  Scenario: Check if user able to retrieve a record with valid LMS API for Assignment Module
    Given User creates GET Request for the LMS API Assignment Module
    When  User sends HTTPS Request for getting all assignments
    Then  User receives 200 OK Status with response body for Assignment Module
    
  @Assignment_TC_010
  Scenario: Check if user able to retrieve a record with invalid LMS API endpoint for Assignment Module
    Given User creates GET Request for the LMS API invalid endpoint Assignment Module
    When  User sends HTTPS Request for getting all assignments with invalid endpoint
    Then  User should receives 404 Not Found status with error message
    
  @Assignment_TC_011
  Scenario: Check if user able to retrieve a record with valid Assignment ID for Assignment Module
    Given User creates GET Request for the LMS API endpoint with valid Assignment ID
    When  User sends HTTPS Request for getting all assignments by assignment id
    Then  User receives 200 OK Status for valid id with response body for Assignment Module

  @Assignment_TC_012
  Scenario: Check if user able to retrieve a record with invalid Assignment ID for Assignment Module
    Given User creates GET Request for the LMS API endpoint with valid Assignment ID
    When  User sends HTTPS Request for getting all assignments by invalid assignment id
    Then  User receives 404 Not Found Status with message and boolean success details for invalid assignment Id

  @Assignment_TC_013
  Scenario: Check if user able to retrieve a record with valid BATCH ID for Assignment Module
    Given User creates GET Request for the LMS API endpoint with valid Batch Id for Assignment Module
    When  User sends HTTPS Request for getting all assignments by batch id
    Then  User receives 200 OK Status with response body for Assignment Module for valid batch id
    
  @Assignment_TC_014
  Scenario: Check if user able to retrieve a record with invalid BATCH ID for Assignment Module
    Given User creates GET Request for the LMS API endpoint with valid Batch Id for Assignment Module
    When  User sends HTTPS Request for getting all assignments by invalid batch id
    Then  User receives 404 Not Found Status with message and boolean success details for invalid batch id

  @Assignment_TC_015
  Scenario: Check if user able to retrieve a record with valid BATCH ID with no assignments for Assignment Module
    Given User creates GET Request for the LMS API endpoint with valid Batch Id for Assignment Module
    When  User sends HTTPS Request for getting all assignments by batch id
    Then  User receives 404 Not Found Status with message and boolean success details for batch id with no assignments
    
  @Assignment_TC_016
  Scenario: Check if user able to update a record with valid AssignmentID and mandatory request body
    Given User creates PUT Request for the LMS API endpoint and Valid Assignment Id for Assignment Module
    When  User sends PUT Request and request Body for Assignment Module with updated fields
    Then  User receives 200 OK Status with updated value in response body for Assignment Module
    
     
  @Assignment_TC_017
  Scenario Outline: Check if user able to update a record with invalid AssignmentID and mandatory request body
    Given User creates PUT Request for the LMS API endpoint and Valid Assignment Id for Assignment Module
    When  User sends PUT Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber>
    Then  User receives 404 Not Found Status with message and boolean success details for invalid assignment id for update
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         8 |
      
  @Assignment_TC_018
  Scenario Outline: Check if user able to update a record with valid AssignmentID and missing mandatory field Assignment name for Assignment Module
    Given User creates PUT Request for the LMS API endpoint and Valid Assignment Id for Assignment Module
    When  User sends PUT Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber> for negative
    Then  User receives 400 Bad Request Status with message and boolean success details for missing mandatory field assignment name for update
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |         9 |
      
  @Assignment_TC_019
  Scenario Outline: Check if user able to update a record with valid AssignmentID and missing mandatory field Assignment Description for Assignment Module
    Given User creates PUT Request for the LMS API endpoint and Valid Assignment Id for Assignment Module
    When  User sends PUT Request and request Body for Assignment Module from excel "<Sheetname>" and <RowNumber> for negative
    Then  User receives 400 Bad Request Status with message and boolean success details for missing mandatory field assignment description for update
    
      Examples: 
      | Sheetname						 | RowNumber |
      | AssignmentModule     |        10 |
      
  @Assignment_TC_020
  Scenario: Check if user able to delete a record with valid Assignment ID for Assignment Module
    Given User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id for Assignment Module
    When  User sends HTTPS Request for deleting assignments by assignment id
    Then  User receives 200 Ok status with message for valid delete request
    
  @Assignment_TC_021
  Scenario: Check if user able to delete a record with invalid Assignment ID for Assignment Module
    Given User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id for Assignment Module
    When  User sends HTTPS Request for deleting assignments by invalid assignment id
    Then  User receives 404 Not Found Status with message and boolean success details for invaid delete assignment id
   
   @Assignment_TC_022
  Scenario: Check if user able to delete a record with invalid endpoint for Assignment Module
    Given User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id for Assignment Module
    When  UUser sends DELETE Request with invalid endpoint for assignment module
    Then  User receives 404 Not Found Status with message and boolean success details for invaid endpoint for assignment module