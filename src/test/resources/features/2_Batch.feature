@BatchModule
Feature: CURD Operations for Batch Module

  @Batch_TC_001
  Scenario Outline: Check if user able to create a Batch with valid endpoint and request body in Batch Module (non existing values)

    Given User creates POST Request for the LMS API endpoint in Batch Module
    When  User sends HTTPS Request and  request Body for Batch Module from excel "<Sheetname>"                                
    Then  User receives 201 Created Status with response body for Batch Module.
    
    Examples: 
      | Sheetname			 | 
      | BatchModule    |         
  
   @Batch_TC_002
  Scenario Outline: Check if user able to create a Batch with valid endpoint and request body (existing value in Batch Name)

    Given User creates POST Request for the LMS API endpoint in Batch Module
    When  User sends HTTPS Request and  request Body with existing values for Batch Module from excel "<Sheetname>"                                  
    Then  User receives 400 Bad Request Status with message and boolean success details
    
    Examples: 
      | Sheetname			 | 
      | BatchModule    |       
       @Batch_TC_003
  Scenario Outline: Check if user able to create a Batch missing mandatory fields in request body

    Given User creates POST Request for the LMS API endpoint in Batch Module
    When  User sends HTTPS Request and  request Body with missing fields for Batch Module from excel "<Sheetname>"                                  
    Then  User receives 400 Bad Request Status with message and boolean success details missing field
    
    Examples: 
      | Sheetname			 | 
      | BatchModule    |      
  
  #GET REQUEST (All Batches)
  @Batch_TC_004
    Scenario: Check if user able to retrieve all batches  with valid LMS API
    Given User creates GET Request for the LMS API endpoint in Batch Module
    When  User sends HTTPS Request                                 
    Then  User receives 200 OK Status with response body.
   #  GET REQUEST{ by Batch ID }
    @Batch_TC_005
    Scenario: Check if user able to retrieve a batch with valid BATCH ID
    Given User creates GET Request for the LMS API endpoint with valid Batch ID
    When  User sends HTTPS Request with valid Batch ID                                
    Then  User receives 200 OK Status with response body.
    
    @Batch_TC_006
    Scenario: Check if user able to retrieve a batch with invalid BATCH ID

    Given User creates GET Request for the LMS API endpoint with invalid Batch ID
    When  User sends HTTPS Request with invalid Batch ID                             
    Then  User receives 404 Not Found Status with message and boolean success details
    
   #GET  REQUEST { by Batch Name}
    @Batch_TC_007
    Scenario: Check if user able to retrieve a batch with valid BATCH NAME

    Given User creates GET Request for the LMS API endpoint with valid Batch name
    When  User sends HTTPS Request with valid BATCH NAME                            
    Then  User receives 200 OK Status with response body.
    
    @Batch_TC_008
    Scenario: Check if user able to retrieve a batch with invalid BATCH NAME

    Given User creates GET Request for the LMS API endpoint with invalid Batch name
    When  User sends HTTPS Request with invalid Batch name                             
    Then  User receives 404 Not Found Status with message and boolean success details
   #GET  REQUEST { by program Id}   
   @Batch_TC_09
    Scenario: Check if user able to retrieve a batch with valid Program ID

    Given User creates GET Request for the LMS API endpoint with valid Program Id
    When  User sends HTTPS Request with valid Program Id                                
    Then  User receives 200 OK Status with response body.
    
     @Batch_TC_10
    Scenario: Check if user able to retrieve a batch with invalid Program Id

    Given User creates GET Request for the LMS API endpoint with invalid Program Id
    When  User sends HTTPS Request with invalid Program Id                             
    Then  User receives 404 Not Found Status with message and boolean success details
    
    #PUT REQUEST  (Update Batch by batchID)
    @Batch_TC_11
Scenario: Check if user able to update a Batch with valid batchID and mandatory request body

    Given User creates PUT Request for the LMS API endpoint  and Valid batch Id
    When  User sends HTTPS Request and request Body for Batch Module 
    Then  User receives 200 OK Status with updated value in response body. 
    
                
        @Batch_TC_12          
  Scenario: Check if user able to update a Batch with invalid batchID and mandatory request body

    Given User creates PUT Request for the LMS API endpoint  and  invalid batch ID
    When  User sends HTTPS Request and  request Body with invalid batchID and request for Batch Module                                  
    Then  User receives 404 Not Found Status with message and boolean success details
    
    
          
 @Batch_TC_13     
    Scenario: Check if user able to update a Batch with valid batchID and missing mandatory fields request body

    Given User creates PUT Request for the LMS API endpoint  and Valid batch Id
    When  User sends HTTPS Request and request Body with valid batchID and missing mandatory fields for Batch Module                                 
    Then  User receives 400 Bad Request Status with message and boolean success details
    
    
 #DELETE REQUEST  (by Batch ID)
 @Batch_TC_14
   Scenario: Check if user able to delete a Batch with valid BatchId
    Given User creates DELETE Request for the LMS API endpoint  and  valid BatchId
    When  User sends HTTPS Request with valid BatchId                                
    Then  User receives 200 OK Status with response body.
 
 @Batch_TC_15
 Scenario: Check if user able to delete a batch with invalid batchid
 Given user creates delete request for the LMS API endpoint and invalid batchid
 When user sends https request with invalid batchid
 Then user receives 404 not found status 
   
     
  