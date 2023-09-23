@UserModule
Feature: CRUD project for Users Module

@POST_Users
@TC_001
  Scenario: Check if user able to create user with the valid endpoint and request body(non-existing values)
  Given User created POST Request for the LMS API endpoint User
  When User sends HTTPs Request and request body with mandatory and additional fields User
  Then User receives 201 Status with response body User
 
@POST_Users  
@TC_002
  Scenario: Check if user able to create a user missing mandatory fields in request body
  Given User created POST Request for the LMS API endpoint User
  When User sends HTTPS Request and request Body with missing mandatory fields User
  Then User receive "400" Bad Request status with message and boolean succuess details User

@POST_Users 
@TC_003
	Scenario: Check if user able to create a user missing mandatory fields in request body
	Given User created POST Request for the LMS API endpoint User
	When User sends HTTPS Request and request Body with missing users fields User
	Then User receive "400" Bad Request status with message and boolean succuess details User
	
@GET_Users
@TC_004
	Scenario: Check if user able retrieve all user with valid LMS API
	Given User creates GET request for the  LMS API All User endpoint User
	When User sends HTTPS Request User
	Then User receives "200" Ok Status with response body in users module User	
	
@GET_UsersID
@TC_005
	Scenario: Check if User retrieve a user with valid User ID
	Given User creates GET Request for the LMS API endpoind with valid User ID User
	When User sends HTTPS Request with valid User ID User
	Then User receives "200OK" status with Response Body User 

@GET_UsersID 
@TC_006
	Scenario: Check if user retrieve a user with 
	Given User creates GET request for the LMS API endpoint with invalid User ID User
	When User sends HTTPS Request with invalid User ID User
	Then User receives "404 Not found" Status with message and boolean success message User 	


@GET_Staff	
@TC_007	
	Scenario: Check if user able to retrieve a user with valid LMS API
	Given User creates GET Request for the LMS API All Staff endpoint User
	When User sends HTTPS Request with valid Staff User
	Then User receives "200" OK Status with response body User
	

@GET_ByRole
@TC_008
	Scenario: Check if user able to retrieve a user with valid LMS API
	Given User creates GET Request for the LMS API User Roles endpoint User
	When User sends HTTPS Request User Roles User
	Then User receives "200" OK Status with response body	with user roles User
	
@Update_Users	
@TC_009
	Scenario: Check if user able to update a user with valid User ID and request body
	Given User creates PUT Request for the LMS API endpoint User
	When User sends HTTPS Request and  request Body with mandatory and additional  fields User    
	Then User receives "200" OK Status with response body	with valid user id User 

@Update_Users		
@TC_010
	Scenario: Check if user able to update a user with invalid User Id and request body
	Given User creates PUT Request for the LMS API endpoint User
	When User sends HTTPS Request and  request Body with mandatory fields User 
	Then User receives 404 Not Found Status with message and boolean success details User	

@Update_Users		
@TC_011
	Scenario: Check if user able to update a user with valid User ID and missing mandatory fields request body
	Given User creates PUT Request for the LMS API endpoint User
	When User sends HTTPS Request  and request Body  (missing mandatory fields) User
	Then User receives 400 Bad Request Status with message and boolean success details User	

@Update_UserRole	
@TC_012
	Scenario: Check if user able to update a user with valid User Id and request body
	Given User creates PUT Request for the LMS API endpoint with Role id User
	When User sends HTTPS Request and  request Body with valid user id User 
	Then User receives 200 Ok Status with response print message User 

@Update_UserRole	
@TC_013
	Scenario: Check if user able to update a user with invalid User Id and request body
	Given User creates PUT Request for the LMS API endpoint with Role id User
	When User sends HTTPS Request and  request Body User 
	Then User receives 404 Not Found Status with message and boolean success details User		

@Update_UserRole	
@TC_014
	Scenario: Check if user able to update a user with valid User Id and request body (missing field)
	Given User creates PUT Request for the LMS API endpoint with Role id User
	When User sends HTTPS Request and  request Body with missing mandatory fields with valid user id User
	Then User receives 400 Bad Request Status with message and boolean success details User 

@Update_ProgramBatch	
@TC_015
	Scenario: Check if user able to assign user to program batch with valid User Id and request body
	Given User creates PUT Request for the LMS API endpoint program batch User
	When User sends HTTPS Request and  request Body with program batch with valid User Id User
	Then User receives 200 Ok Status with response message User	

@Update_ProgramBatch	
@TC_016
	Scenario: Check if user able to  assign user to program / batch with invalid User Id and request body
	Given User creates PUT Request for the LMS API endpoint program batch User
	When User sends HTTPS Request and  request Body with program batch with invalid User Id User
	Then User receives 404 Not Found Status with message and boolean success details User	

@Update_ProgramBatch	
@TC_017
	Scenario: Check if user able to  assign user to program / batch with valid User Id and request body (missing field)
	Given User creates PUT Request for the LMS API endpoint User
	When User sends HTTPS Request and  request Body with missing fields User 
	Then User receives 400 Bad Request Status with message and boolean success details User

@Delete_UserId		
@TC_018
	Scenario: Check if user able to delete a user with valid User Id
	Given User creates DELETE Request for the LMS API endpoint  and  valid user ID User
	When User sends HTTPS Request valid user ID User
	Then User receives 200 Ok status with message User	

@Delete_UserId	
@TC_019
	Scenario: Check if user able to delete a user with valid LMS API,invalid user Id
	Given User creates DELETE Request for the LMS API endpoint  and  invalid user ID User
	When User sends HTTPS Request invalid user ID User
	Then User receives 404 Not Found Status with message and boolean success details User		
	
	
		