@ProgramModule
Feature: CURD Operations for Program Module

@ProgramModule_Post_Positive_TC_001
Scenario Outline: Check if user able to create a program with valid endpoint and request body in Program module(non existing values)
  Given User creates POST Program Request for the LMS API endpoint
  When  User sends HTTPS Request and request Body for Progam Module from excel "<Sheetname>" and <RowNumber>
  Then User receives 201 Created Status with response body for Program Module
  
     Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule        |         1 |
      
@ProgramModule_Post_Negative_TC_002
Scenario Outline: Check if user able to create a program with valid endpoint and request body (existing values in Program Name)
Given User creates POST Program Request for the LMS API endpoint
When  User sends HTTPS Request and request Body for Progam Module from excel "<Sheetname>" and <RowNumber> with existing program name
Then  User receives 400 Bad Request Status with message and boolean success details for program module

  Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         2 |
      
      
@ProgramModule_Post_Negative_TC_003
Scenario Outline: Check if user able to create a program missing mandatory fields in request body
Given User creates POST Program Request for the LMS API endpoint
When  User sends HTTPS Request and request Body for Progam Module from excel "<Sheetname>" and <RowNumber> with missing mandatory fields
Then  User receives 400 Bad Request Status with message and boolean success details for program module   

  Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         3 |
      
 
 @ProgramModule-GetAllProgram_TC_004 
 Scenario: Check if user able to retrieve all programs with valid LMS API for program module
 Given User creates GET Request for the LMS API endpoint for program module
 When User sends HTTPS Request for getting all programs
 Then User receives 200 OK Status with response body for program module
 
 
@ProgramModule-GetProgramById_TC_005 
 Scenario: Check if user able to retrieve a program with valid program ID for program module
 Given User creates GET Request by program id for the LMS API endpoint for program module
 When User sends HTTPS Request for getting program by program id
 Then User receives 200 OK Status with response body for program module
 
 @ProgramModule-Get_Negative_TC_006
 Scenario: Check if user able to retrieve a program with valid program ID for program module
 Given Check if user able to retrieve a program with invalid valid program ID and LMS API
 When User sends HTTPS Request for getting program by program id with invalid data in program module
 Then User receives 404 Not Found Status with message and boolean success details for program module
  
 
 
 
 @ProgramModule-UpdateProgramByID_TC_007
  Scenario: Check if user able to update a program with valid programID and mandatory request body for Program module
  Given User creates PUT Request for the LMS API endpoint with Valid programID for program module
  When  User sends HTTPS Request and request Body for Progam Module 
  Then User receives 200 OK Status with updated value in response body for program module
  
  

      
      
  @ProgramModule-UpdateProgramByName_TC_008     
 Scenario: Check if user able to update a program with valid programName and mandatory request body for Program module
 Given User creates PUT Request for the LMS API endpoint  and Valid program Name
 When User sends HTTPS Request  and  request Body with mandatory fields. (program Description , program Name, program status)
 Then User receives 200 OK Status with response body for program module
 
 
 
 
 @ProgramModule-UpdateProgramByMissingMendatoryFields_Negative_TC_009
 Scenario Outline: Check if user able to update a program  missing mandatory fields in request body
 Given User creates PUT Request for the LMS API endpoint  and Valid programID 
 When User sends HTTPS Request  and request Body missing mandatory fields from excel "<Sheetname>" and <RowNumber>
 Then User receives 400 Bad Request Status with message and boolean success details for program module
 
   Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         8 |      
 
 
 @ProgramModule-UpdateProgramByID_Negative_TC_010
 Scenario Outline: Check if user able to update a program with invalid programID and mandatory request body
 Given User creates PUT Request for the LMS API endpoint  and inValid programID for program module
 When User sends HTTPS Request and request Body to update Progam with invalid program id from excel "<Sheetname>" and <RowNumber>
 Then User receives 404 Not Found Status with message and boolean success details for program module
 
 Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         7 |
 

@ProgramModule-UpdateProgramByinvalidprogramNameandrequestbody_Negative_TC_0011
 Scenario Outline: Check if user able to update a program with  invalid program Name and request body
Given User creates PUT Request for the LMS API endpoint  and InValid program Name
When User sends HTTPS Request  and  request Body with Invalid (program Description , program Name, program status)from excel "<Sheetname>" and <RowNumber>
Then User receives 404 Not Found Status with message and boolean success details Program


Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         5 |      
 

 @ProgramModule-Updateprogramname-missingmandatoryfields_Negative_TC_012
 Scenario Outline: Check if user able to update a program  missing mandatory fields in request body
 Given User creates PUT Request for the LMS API endpoint  and Valid program Name
 When User sends HTTPS Request  and request Body missing mandatory fields from excel "<Sheetname>" and <RowNumber>      
 Then User receives 400 Bad Request Status with message and boolean success details Program
 Examples: 
      | Sheetname						 | RowNumber |
      | ProgramModule     	 |         3 |      
      
      

 
@ProgramModule-DeleteProgramByID_TC_013
Scenario: Check if user able to delete a program with valid program ID for program module
Given User creates DELETE Request for the LMS API endpoint  and  valid program ID for program module
When User sends HTTPS Request for delete program by program id
Then User receives 200 Ok status with message


@ProgramModule-DeleteProgramByID_Negative_TC_014
Scenario: Check if user able to delete a program with valid LMS API,invalid program id for program module
Given User creates DELETE Request for the LMS API endpoint  and  invalid {programId} for program module
When User sends HTTPS Request for delete program by invalid program id
Then User receives 404 Not Found Status with message and boolean success details for invalid program id in program module



#@ProgramModule-DeleteProgramByName_Positive_TC_015
  #Scenario: To Delete Program By ProgramName
    #Given User ensures to perform POST operation with body as
  #	| programName  | programDescription | programStatus | 
  #	| PostgreSQL215 | Learn PostgreSql14 | Active        |
    #When User sends the post request using "/saveprogram" as 
    #Then Status code should come as 201 ok
    #Then User performs Delete operation to clear the porgram for the url "/deletebyprogname" 


@ProgramModule-DeleteProgramByName_Negative_TC_016
Scenario: Check if user able to delete a program with Invalid programName for program module
Given User creates DELETE Request for the LMS API endpoint  and  Invalid programName for program module
When User sends HTTPS Request and request Body for Progam to perform delete by InvalidProgramname operation
Then User receives 404 Not Found Status with message and boolean success details Program

 
  
 