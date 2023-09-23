package api.stepdefinitions;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;

import org.json.JSONTokener;

import api.utilities.LoggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class Users_Stepdefinition {
	
	Response response;
	String baseUrl = "BaseUrl";
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("config"); // Load properties file  // name of the properties file
		return routes;
	}
	
	//TC_001 POST Request 
	@Given("User created POST Request for the LMS API endpoint User")
	public void user_created_post_request_for_the_lms_api_endpoint_User() {
		
		LoggerLoad.info("******** User Module Starts********");
		String post_url=getURL().getString("Post_Users");
		System.out.println(baseUrl+post_url);
	}

	@When("User sends HTTPs Request and request body with mandatory and additional fields User")
	public void user_sends_htt_ps_request_and_request_body_with_mandatory_and_additional_fields() throws FileNotFoundException {
		
		File file = new File("user_post.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.post("/users/users/roleStatus");
			
	}

	@Then("User receives {int} Status with response body User")
	public void user_receives_status_with_response_body(Integer int1) {
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
		LoggerLoad.info("Received 200 ok success status");
	}

	//TC_002 POST Request 
	@When("User sends HTTPS Request and request Body with missing mandatory fields User")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_fields() throws FileNotFoundException {
	    
		File file = new File("usersInvalidModules.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.post("/users/users/roleStatus");
		
	}

	@Then("User receive {string} Bad Request status with message and boolean succuess details User")
	public void user_receive_bad_request_status_with_message_and_boolean_succuess_details(String string) {
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
		LoggerLoad.info("Received 400 bad request status");
	}
	
	//TC_003 POST Request 
	@When("User sends HTTPS Request and request Body with missing users fields User")
	public void user_sends_https_request_and_request_body_with_missing_users_fields() throws FileNotFoundException {
	    
		File file = new File("usersInvalidModules.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.post("/users/users/roleStatus");
		
	}

	//TC_004-GET Request
	@Given("User creates GET request for the  LMS API All User endpoint User")
	public void user_creates_get_request_for_the_lms_api_all_user_endpoint() {
	    
		String Get_UserURL = getURL().getString("Get_Users");
		System.out.println(Get_UserURL);
	}

	@When("User sends HTTPS Request User")
	public void user_sends_https_request() {
		
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				 .when()
					.get("/users/users");
	}

	@Then("User receives {string} Ok Status with response body in users module User")
	public void user_receives_ok_status_with_response_body_in_users_module(String string) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
		LoggerLoad.info("All Users Data");	
	}

	//TC_005-GET Request with user Id
	@Given("User creates GET Request for the LMS API endpoind with valid User ID User")
	public void user_creates_get_request_for_the_lms_api_endpoind_with_valid_user_id() {
	    
		String Get_byUserIdURL = getURL().getString("Get_byUserId");
		System.out.println("Get Users by user ID : "+Get_byUserIdURL);
	}
	
	@When("User sends HTTPS Request with valid User ID User")
	public void user_sends_https_request_with_valid_user_id() {
	    
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.pathParam("userId", "U9365")
				.header("Content-Type","application/json")
				
				.when()
					.get("/users/users/{userId}");
		
	}
	
	@Then("User receives {string} status with Response Body User")
	public void user_receives_status_with_response_body(String string) {
	   
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
		response.then().log().all();
	}
	
	//TC_006-GET Request with user Id
	@Given("User creates GET request for the LMS API endpoint with invalid User ID User")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_user_id() {
	    
		String Get_byUserIdURL = getURL().getString("Get_byUserId");
	}
	
	@When("User sends HTTPS Request with invalid User ID User")
	public void user_sends_https_request_with_invalid_user_id() {

		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.pathParam("userId", "U9305")
				.header("Content-Type","application/json")
				
				.when()
					.get("/users/users/{userId}");
	}

	@Then("User receives {string} Status with message and boolean success message User")
	public void user_receives_status_with_message_and_boolean_success_message(String string) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
		response.then().log().all();
	}

	//TC_007-GET staff Request 
	@Given("User creates GET Request for the LMS API All Staff endpoint User")
	public void user_creates_get_request_for_the_lms_api_all_staff_endpoint() {
	    
		System.out.println("BaseUrl"+"Get_AllStaff");
	}
	
	@When("User sends HTTPS Request with valid Staff User")
	public void user_sends_https_request_with_valid_staff() {
	    
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.header("Content-Type","application/json")
				.when()
				.get("/users/users/getAllStaff");
		
	}

	@Then("User receives {string} OK Status with response body User")
	public void user_receives_ok_status_with_response_body(String string) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
	}

	//TC_008-GET Request with role
	@Given("User creates GET Request for the LMS API User Roles endpoint User")
	public void user_creates_get_request_for_the_lms_api_user_roles_endpoint() {

		String Get_UsersWithRole = getURL().getString("Get_byRole");
		System.out.println("Get users by Roles "+Get_UsersWithRole);
	}
	
	@When("User sends HTTPS Request User Roles User")
	public void user_sends_https_request_user_roles() {

		
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.header("Content-Type","application/json")
				.when()
					.get("/users/users/roles");
	}
	
	@Then("User receives {string} OK Status with response body	with user roles User")
	public void user_receives_ok_status_with_response_body_with_user_roles(String string) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
	}

	//TC_009-Update User Request 
	@Given("User creates PUT Request for the LMS API endpoint User")
	public void user_creates_put_request_for_the_lms_api_endpoint() {
		
		String Update_users = getURL().getString("Update_User");
		System.out.println("User update end point uri is :"+Update_users);
	    
	}

	@When("User sends HTTPS Request and  request Body with mandatory and additional  fields User")
	public void user_sends_https_request_and_request_body_with_mandatory_and_additional_fields() throws FileNotFoundException {
	    
		File file = new File("Update_users.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", "U9365")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/{userId}");
		
	}
	@Then("User receives {string} OK Status with response body	with valid user id User")
	public void user_receives_ok_status_with_response_body_with_valid_user_id(String string) {
	   
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		response.then().log().all();
	}
	
	//TC_010-Update User Request
	@When("User sends HTTPS Request and  request Body with mandatory fields User")
	public void user_sends_https_request_and_request_body_with_mandatory_fields() throws FileNotFoundException {
	   
		File file = new File("Update_users.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", "96548")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/{userId}");
	}
	@Then("User receives {int} Not Found Status with message and boolean success details User")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
		LoggerLoad.info("******** User Module Ends********");
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
		response.then().log().all();
	}

	//TC_011-Update User Request
	@When("User sends HTTPS Request  and request Body  \\(missing mandatory fields) User")
	public void user_sends_https_request_and_request_body_missing_mandatory_fields() throws FileNotFoundException {
	    
		File file = new File("update_InvalidUsers.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
					
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", "U9365")
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/{userId}");
		
	}
	@Then("User receives {int} Bad Request Status with message and boolean success details User")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
		response.then().log().all();			
	}
	
	//TC_012-Update User role Request
	@Given("User creates PUT Request for the LMS API endpoint with Role id User")
	public void user_creates_put_request_for_the_lms_api_endpoint_with_role_id() {
	    
		String update_userRoles = getURL().getString("Update_UserRole");
		System.out.println("Updating user Role Id and Role status with this endpoint: "+update_userRoles);
		
	}
	
	
	@When("User sends HTTPS Request and  request Body with valid user id User")
	public void user_sends_https_request_and_request_body_with_valid_user_id() throws FileNotFoundException {
	    
		File file = new File("update_Role.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		String userID ="U9772";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userID", userID)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleStatus/{userID}");
       
	}
	@Then("User receives {int} Ok Status with response print message User")
	public void user_receives_ok_status_with_response_print_message(Integer int1) {
	   
		boolean status = false;
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);

        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	//TC_013-Update User Request
	@When("User sends HTTPS Request and  request Body User")
	public void user_sends_https_request_and_request_body() throws FileNotFoundException {
	   
		File file = new File("update_Role.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		boolean status = false;
		String userID ="U93657";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userID", userID)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleStatus/{userID}");
	}
	
	//TC_014-Update User Request
	@When("User sends HTTPS Request and  request Body with missing mandatory fields with valid user id User")
	public void user_sends_https_request_and_request_body_with_missing_mandatory_fields_with_valid_user_id() throws FileNotFoundException {
	   
		File file = new File("Update_InvalidRole.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		String userID ="U93657";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userID", userID)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleStatus/{userID}");
        
	}
	
	//TC_015-Update program batch Request
	@Given("User creates PUT Request for the LMS API endpoint program batch User")
	public void user_creates_put_request_for_the_lms_api_endpoint_program_batch() {

		String update_ProgramStatus = getURL().getString("Update_ProgramBatch");
		System.out.println("update program batch status url:"+update_ProgramStatus);
		
	}
	@When("User sends HTTPS Request and  request Body with program batch with valid User Id User")
	public void user_sends_https_request_and_request_body_with_program_batch_with_valid_user_id() throws FileNotFoundException {
	    
		File file = new File("Update_UserRole.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		String userId ="U9772";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", userId)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleProgramBatchStatus/{userId}");
	}

	@Then("User receives {int} Ok Status with response message User")
	public void user_receives_ok_status_with_response_message(Integer expectedStatusCode) {
		
		//response.then().statusCode(200);
	    String responseBody = response.getBody().asString();
	    System.out.println("Response Body: " + responseBody);
	
	}

	//TC_016-Update program batch Request
	@When("User sends HTTPS Request and  request Body with program batch with invalid User Id User")
	public void user_sends_https_request_and_request_body_with_program_batch_with_invalid_user_id() throws FileNotFoundException {
	   
		File file = new File("Update_UserRole.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		String userId ="U9777";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", userId)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleProgramBatchStatus/{userId}");
	}
	
	//TC_017-Update program batch Request
	@When("User sends HTTPS Request and  request Body with missing fields User")
	public void user_sends_https_request_and_request_body_with_missing_fields() throws FileNotFoundException {
	    
		File file = new File("Update_InvalidUserRole.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
				
		String userId ="U9772";
        response = given()
        		.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
        		.pathParam("userId", userId)
			.header("Content-Type","application/json")
			.body(payload.toString())
		.when()
			.put("/users/users/roleProgramBatchStatus/{userId}");
		
	}
	
	//TC_018 Delete request
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid user ID User")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_user_id() {
	   
		String Delete_Users = getURL().getString("Delete_Users");
		System.out.println("Delete user endpoint :"+Delete_Users);
	}
	@When("User sends HTTPS Request valid user ID User")
	public void user_sends_https_request_valid_user_id() {
	    
		String userId = "U9365";
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.when()
				.delete("/users/users/"+ userId);
				
	}
	@Then("User receives {int} Ok status with message User")
	public void user_receives_ok_status_with_message(Integer int1) {
	    
		int statusCode = response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		
	}
	
	//TC_019 Delete request
	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid user ID User")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid() {
	   
		String Delete_Users = getURL().getString("Delete_Users");
		System.out.println("Delete user endpoint :"+Delete_Users);
	}
	@When("User sends HTTPS Request invalid user ID User")
	public void user_sends_https_request_invalid_user_id() {
	    
		String userId = "U93655";
		response = given()
				.baseUri("https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms")
				.when()
				.delete("/users/users/"+ userId);
	}

	
	}



