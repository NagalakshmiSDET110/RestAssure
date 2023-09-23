package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.ResourceBundle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import com.github.javafaker.Faker;
import api.payload.Assignment_Payload;
import api.utilities.Constants;
import api.utilities.LoggerLoad;
import api.utilities.XLUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class Assignment_Stepdefinition {
	
	public static Assignment_Payload payload = new Assignment_Payload();
	Batch_Stepdefinition Batch_Module = new Batch_Stepdefinition();
	Faker faker = new Faker();
	Response response;
	
	static ResourceBundle getURL()
	{
		LoggerLoad.info("******** Assignment Module ********");
		LoggerLoad.info("Loading the Properties Files");
		ResourceBundle routes= ResourceBundle.getBundle("config"); // Load properties file  
		return routes;
	}

	@Given("User creates POST Request for the LMS API Assignment Module")
	public void user_creates_post_request_for_the_lms_api_assignment_module_1() {
		
		LoggerLoad.info("Getting the Post URL");		
		Constants.post_url=getURL().getString("POST_Assignment");
		
	}
	
	@When("User sends HTTPS Request and request Body for Assignment Module through dynamic variables")
	public void user_sends_https_request_and_request_body_for_assignment_module_through_dynamic_variables_1() {
		
		LoggerLoad.info("Sending the Post Request with payload from faker");
		payload.setAssignmentDescription(Constants.AssignDesc+(faker.idNumber().hashCode()));
		payload.setAssignmentName(Constants.AssignName+(faker.idNumber().hashCode()));
		payload.setBatchId(Batch_Stepdefinition.BatchID_Chaining);
		payload.setComments(Constants.Comments);
		payload.setCreatedBy(Constants.CreatedBy);
		payload.setDueDate(Constants.Duedate);
		payload.setGraderId(Constants.GraderID);
		payload.setPathAttachment1(Constants.PathAttach1);
		payload.setPathAttachment2(Constants.PathAttach2);
		payload.setPathAttachment3(Constants.PathAttach3);
		payload.setPathAttachment4(Constants.PathAttach4);
		payload.setPathAttachment5(Constants.PathAttach5);
		
		response = given()
				.header("Content-Type",Constants.ExpectedContentType)
				.body(payload)
			.when()
				.post(Constants.post_url);
		
		Constants.Assignment_Id_Chaining = response.jsonPath().getInt("assignmentId");
	    
	}

	@When("User sends HTTPS Request and request Body for Assignment Module from excel {string} and {int}")
	public void user_sends_https_request_and_request_body_for_assignment_module_from_excel__1and(String Sheetname, Integer Rowno) throws InvalidFormatException, IOException {
		
		LoggerLoad.info("Sending posting request with payload from Excel");
		getAllData(Sheetname,Rowno);
		payload.setBatchId(Integer.parseInt(Constants.Excel_Batch_ID));
		
		System.out.println("Batch id in row 5 :" +payload.getBatchId());
		
		response = given()
				.header("Content-Type",Constants.ExpectedContentType)
				.body(payload)
			.when()
				.post(Constants.post_url);
				
	}

	@Then("User receives {int} Created Status with response body for Assignment Module.")
	public void user_receives_created_status_with_response_body_for_assignment_module_1(Integer int1) {
		
		LoggerLoad.info("Validating the Post Response");
		response.then().log().all();
		Constants.ExpectedAssignmentId = response.jsonPath().getInt("assignmentId");
		Constants.ExpectedAssignmentDescription = response.jsonPath().get("assignmentDescription");
		Constants.ExpectedAssignmentName = response.jsonPath().get("assignmentName");
		Constants.ExpectedBatchId = response.jsonPath().getInt("batchId");
		Constants.ExpectedcreatedBy = response.jsonPath().get("createdBy");
		Constants.ExpectedgraderId = response.jsonPath().get("graderId");
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode1);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine1);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);

		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("assignmentDescription"),Constants.ExpectedAssignmentDescription);
		assertEquals(response.jsonPath().get("assignmentName"),Constants.ExpectedAssignmentName);
		assertEquals(response.jsonPath().getInt("batchId"),payload.getBatchId());
		assertEquals(response.jsonPath().get("comments"),payload.getComments());
		assertEquals(response.jsonPath().get("createdBy"),payload.getCreatedBy());
		assertEquals(response.jsonPath().get("dueDate"),payload.getDueDate());
		assertEquals(response.jsonPath().get("graderId"),payload.getGraderId());
		assertEquals(response.jsonPath().get("pathAttachment1"),payload.getPathAttachment1());
		assertEquals(response.jsonPath().get("pathAttachment2"),payload.getPathAttachment2());
		assertEquals(response.jsonPath().get("pathAttachment3"),payload.getPathAttachment3());
		assertEquals(response.jsonPath().get("pathAttachment4"),payload.getPathAttachment4());
		assertEquals(response.jsonPath().get("pathAttachment5"),payload.getPathAttachment5());
		

		
	}
	
	@Then("User receives {int} Bad Request Status with message and boolean success details for already existing assignment name")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_already_existing_assignment_name_1(Integer int1) {
	   
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode2);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine2);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message1);
		assertEquals(response.jsonPath().get("success"),false);
		
	}


	@Then("User receives {int} Bad Request Status with message and boolean success details for missing fields - Assignment Name")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_missing_fields_assignment_name_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode2);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine2);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message2);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for missing fields - Assignment Description")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_missing_fields_assignment_description_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode2);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine2);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message3);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Then("User receives {int} Internal Server Error with message and boolean success details for invalid batch id field")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_invalid_batch_id_field_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
				
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema3));
		
		//Response body Validation
		assertEquals(response.jsonPath().getInt("status"),Constants.ExpectedStatusCode3);
		assertEquals(response.jsonPath().get("error"),Constants.Message4);
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invalid created by field")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_invalid_created_by_field_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message5);
		assertEquals(response.jsonPath().get("success"),false);
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invalid grader id field")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_invalid_graderid_field_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Post Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message6);
		assertEquals(response.jsonPath().get("success"),false);
	}

	@Given("User creates GET Request for the LMS API Assignment Module")
	public void user_creates_get_request_for_the_lms_api_assignment_module_1() {
	    
		LoggerLoad.info("Getting the Get All Assignments URL");
		Constants.get_all_assignments_url=getURL().getString("GET_All_Assignments");
		
	}

	@When("User sends HTTPS Request for getting all assignments")
	public void user_sends_https_request_for_getting_all_assignments_1() {
		
		LoggerLoad.info("Sending the Get Request for all Assignments");
		response = given()
				  .when()
				    .get(Constants.get_all_assignments_url);
		
		Constants.Response_AssignId = response.jsonPath().getInt("assignmentId[1]");
		Constants.Response_AssignDesc= response.jsonPath().get("assignmentDescription[1]");
		Constants.Response_AssignName= response.jsonPath().get("assignmentName[1]");
		Constants.Response_Comments= response.jsonPath().get("comments[1]");
		Constants.Response_Duedate= response.jsonPath().get("dueDate[1]");	
		Constants.Response_BatchId= response.jsonPath().getInt("batchId[1]");
		Constants.Response_CreatedBy= response.jsonPath().get("createdBy[1]");	
		Constants.Response_GraderID= response.jsonPath().get("graderId[1]");
		Constants.Response_PathAttach1=response.jsonPath().get("pathAttachment1[1]");		
		Constants.Response_PathAttach2=response.jsonPath().get("pathAttachment2[1]");		
		Constants.Response_PathAttach3=response.jsonPath().get("pathAttachment3[1]");	
		Constants.Response_PathAttach4=response.jsonPath().get("pathAttachment4[1]");		
		Constants.Response_PathAttach5=response.jsonPath().get("pathAttachment5[1]");
	    
	}

	@Then("User receives {int} OK Status with response body for Assignment Module")
	public void user_receives_ok_status_with_response_body_for_assignment_module_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Get Response for Positive scenario");
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode5);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine4);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);

		//Response body validation
		assertEquals(response.jsonPath().get("assignmentDescription[1]"),Constants.Response_AssignDesc);
		assertEquals(response.jsonPath().get("assignmentName[1]"),Constants.Response_AssignName);
		assertEquals(response.jsonPath().getInt("batchId[1]"),Constants.Response_BatchId);
		assertEquals(response.jsonPath().get("comments[1]"),Constants.Response_Comments);
		assertEquals(response.jsonPath().get("createdBy[1]"),Constants.Response_CreatedBy);
		assertEquals(response.jsonPath().get("dueDate[1]"),Constants.Response_Duedate);
		assertEquals(response.jsonPath().get("graderId[1]"),Constants.Response_GraderID);
		assertEquals(response.jsonPath().get("pathAttachment1[1]"),Constants.Response_PathAttach1);
		assertEquals(response.jsonPath().get("pathAttachment2[1]"),Constants.Response_PathAttach2);
		assertEquals(response.jsonPath().get("pathAttachment3[1]"),Constants.Response_PathAttach3);
		assertEquals(response.jsonPath().get("pathAttachment4[1]"),Constants.Response_PathAttach4);
		assertEquals(response.jsonPath().get("pathAttachment5[1]"),Constants.Response_PathAttach5);
		
	}

	@Given("User creates GET Request for the LMS API invalid endpoint Assignment Module")
	public void user_creates_get_request_for_the_lms_api_invalid_endpoint_assignment_module_1() {
	    
		LoggerLoad.info("Getting the invalid endpoint for Get");
		Constants.Invalid_Endpoint_url=getURL().getString("Invalid_Endpoint");
	}

	@When("User sends HTTPS Request for getting all assignments with invalid endpoint")
	public void user_sends_https_request_for_getting_all_assignments_with_invalid_endpoint_1() {
		
		LoggerLoad.info("Sending the Get Request for Negative scenario");	    
		response = given()
				  .when()
				    .get(Constants.Invalid_Endpoint_url);
		
	}

	@Then("User should receives {int} Not Found status with error message")
	public void user_should_receives_not_found_status_with_error_message_1(Integer int1) {
		
		LoggerLoad.info("Validating the Get Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
	    
	}

	@Given("User creates GET Request for the LMS API endpoint with valid Assignment ID")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_assignment_id_1() {
		
		LoggerLoad.info("Getting the Get URL for Get by ID");
		Constants.Get_By_Id_url=getURL().getString("GET_By_ID");
	    
	}

	@When("User sends HTTPS Request for getting all assignments by assignment id")
	public void user_sends_https_request_for_getting_all_assignments_by_assignment_id_1() {
	    
		LoggerLoad.info("Sending the Get Request");
		response = given()
				.pathParam("assignmentId", Constants.ExpectedAssignmentId)
			.when()
				.get(Constants.Get_By_Id_url);
		
			
		Constants.Response_AssignId = response.jsonPath().getInt("assignmentId");
		Constants.Response_AssignDesc= response.jsonPath().get("assignmentDescription");
		Constants.Response_AssignName= response.jsonPath().get("assignmentName");
		Constants.Response_Comments= response.jsonPath().get("comments");
		Constants.Response_Duedate= response.jsonPath().get("dueDate");	
		Constants.Response_BatchId= response.jsonPath().getInt("batchId");
		Constants.Response_CreatedBy= response.jsonPath().get("createdBy");	
		Constants.Response_GraderID= response.jsonPath().get("graderId");
		Constants.Response_PathAttach1=response.jsonPath().get("pathAttachment1");		
		Constants.Response_PathAttach2=response.jsonPath().get("pathAttachment2");		
		Constants.Response_PathAttach3=response.jsonPath().get("pathAttachment3");	
		Constants.Response_PathAttach4=response.jsonPath().get("pathAttachment4");		
		Constants.Response_PathAttach5=response.jsonPath().get("pathAttachment5");
			
	}
	
	@Then("User receives {int} OK Status for valid id with response body for Assignment Module")
	public void user_receives_ok_status_for_valid_id_with_response_body_for_assignment_module_1(Integer int1) {

		LoggerLoad.info("Validating the Get Response for Positive scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode5);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine4);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema));

		//Response body validation
		assertEquals(response.jsonPath().get("assignmentDescription"),Constants.Response_AssignDesc);
		assertEquals(response.jsonPath().get("assignmentName"),Constants.Response_AssignName);
		assertEquals(response.jsonPath().getInt("batchId"),Constants.Response_BatchId);
		assertEquals(response.jsonPath().get("comments"),Constants.Response_Comments);
		assertEquals(response.jsonPath().get("createdBy"),Constants.Response_CreatedBy);
		assertEquals(response.jsonPath().get("dueDate"),Constants.Response_Duedate);
		assertEquals(response.jsonPath().get("graderId"),Constants.Response_GraderID);
		assertEquals(response.jsonPath().get("pathAttachment1"),Constants.Response_PathAttach1);
		assertEquals(response.jsonPath().get("pathAttachment2"),Constants.Response_PathAttach2);
		assertEquals(response.jsonPath().get("pathAttachment3"),Constants.Response_PathAttach3);
		assertEquals(response.jsonPath().get("pathAttachment4"),Constants.Response_PathAttach4);
		assertEquals(response.jsonPath().get("pathAttachment5"),Constants.Response_PathAttach5);
	}

	@When("User sends HTTPS Request for getting all assignments by invalid assignment id")
	public void user_sends_https_request_for_getting_all_assignments_by_invalid_assignment_id_1() {
		
		LoggerLoad.info("Sending the Get Request for  Negative scenario");
		response = given()
				.pathParam("assignmentId", Constants.Invalid_Assignment_Id)
			.when()
				.get(Constants.Get_By_Id_url);
	}
	
	@Then("User receives {int} Not Found Status with message and boolean success details for invalid assignment Id")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invalid_assignment_id_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Get Response for Negative scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message7);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Given("User creates GET Request for the LMS API endpoint with valid Batch Id for Assignment Module")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id_for_assignment_module_1() {
	    
		LoggerLoad.info("Geting the Get URL for Get by Batch Id");
		Constants.Get_By_BatchId_url=getURL().getString("GET_By_BatchId");
		
	}

	@When("User sends HTTPS Request for getting all assignments by batch id")
	public void user_sends_https_request_for_getting_all_assignments_by_batch_id_1() {
		
		LoggerLoad.info("Sending the Get Request for Positive Scenario");
		response = given()
				.pathParam("batchID", Batch_Stepdefinition.BatchID_Chaining)
			.when()
				.get(Constants.Get_By_BatchId_url);
	    
	}

	@Then("User receives {int} OK Status with response body for Assignment Module for valid batch id")
	public void user_receives_ok_status_with_response_body_for_assignment_module_for_valid_batch_id_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Get Response for Positive Scenario");
		Constants.Response_AssignId = response.jsonPath().getInt("[0].assignmentId");
		Constants.Response_AssignDesc= response.jsonPath().get("[0].assignmentDescription");
		Constants.Response_AssignName= response.jsonPath().get("[0].assignmentName");
		Constants.Response_Comments= response.jsonPath().get("[0].comments");
		Constants.Response_Duedate= response.jsonPath().get("[0].dueDate");	
		Constants.Response_BatchId= response.jsonPath().getInt("[0].batchId");
		Constants.Response_CreatedBy= response.jsonPath().get("[0].createdBy");	
		Constants.Response_GraderID= response.jsonPath().get("[0].graderId");
		Constants.Response_PathAttach1=response.jsonPath().get("[0].pathAttachment1");		
		Constants.Response_PathAttach2=response.jsonPath().get("[0].pathAttachment2");		
		Constants.Response_PathAttach3=response.jsonPath().get("[0].pathAttachment3");	
		Constants.Response_PathAttach4=response.jsonPath().get("[0].pathAttachment4");		
		Constants.Response_PathAttach5=response.jsonPath().get("[0].pathAttachment5");
		
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode5);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine4);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Put_Schema1));

		//Response body validation
		assertEquals(response.jsonPath().get("[0].assignmentDescription"),Constants.Response_AssignDesc);
		assertEquals(response.jsonPath().get("[0].assignmentName"),Constants.Response_AssignName);
		assertEquals(response.jsonPath().getInt("[0].batchId"),Constants.Response_BatchId);
		assertEquals(response.jsonPath().get("[0].comments"),Constants.Response_Comments);
		assertEquals(response.jsonPath().get("[0].createdBy"),Constants.Response_CreatedBy);
		assertEquals(response.jsonPath().get("[0].dueDate"),Constants.Response_Duedate);
		assertEquals(response.jsonPath().get("[0].graderId"),Constants.Response_GraderID);
		assertEquals(response.jsonPath().get("[0].pathAttachment1"),Constants.Response_PathAttach1);
		assertEquals(response.jsonPath().get("[0].pathAttachment2"),Constants.Response_PathAttach2);
		assertEquals(response.jsonPath().get("[0].pathAttachment3"),Constants.Response_PathAttach3);
		assertEquals(response.jsonPath().get("[0].pathAttachment4"),Constants.Response_PathAttach4);
		assertEquals(response.jsonPath().get("[0].pathAttachment5"),Constants.Response_PathAttach5);
		
	}

	@When("User sends HTTPS Request for getting all assignments by invalid batch id")
	public void user_sends_https_request_for_getting_all_assignments_by_invalid_batch_id_1() {
		
		LoggerLoad.info("Sending the Get Request for Negative Scenario");
		response = given()
				.pathParam("batchID", Constants.Invalid_BatchId)
			.when()
				.get(Constants.Get_By_BatchId_url);
	    
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invalid batch id")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invalid_batch_id_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Get Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message8);
		assertEquals(response.jsonPath().get("success"),false);
		
		Batch_Stepdefinition.BatchID_Chaining = Constants.Batch_With_No_Assignments;
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for batch id with no assignments")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_batch_id_with_no_assignments_1(Integer int1) {
	   
		LoggerLoad.info("Validating the Get Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message9);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Given("User creates PUT Request for the LMS API endpoint and Valid Assignment Id for Assignment Module")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_assignment_id_for_assignment_module_1() {
	   
		LoggerLoad.info("Getting the PUT URL for Updating the record");
		Constants.put_url=getURL().getString("PUT_Assignment");
				
	}

	@When("User sends PUT Request and request Body for Assignment Module with updated fields")
	public void user_sends_put_request_and_request_body_for_assignment_module_with_updated_fields_1() {
		
		LoggerLoad.info("Sending the Put Request for Positive Scenario");
		payload.setAssignmentId(Constants.ExpectedAssignmentId);
		payload.setGraderId(Constants.ExpectedgraderId);
		payload.setAssignmentDescription(Constants.AssignDesc+(faker.idNumber().hashCode()));
	 
		response = given()
				.header("Content-Type",Constants.ExpectedContentType)
				.pathParam("assignmentId", Constants.ExpectedAssignmentId)
				.body(payload)
		.when()
				.put(Constants.put_url);
	    
	}

	@Then("User receives {int} OK Status with updated value in response body for Assignment Module")
	public void user_receives_ok_status_with_updated_value_in_response_body_for_assignment_module_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Put Response for Positive Scenario");
		response.then().log().all();
		Constants.ExpectedAssignmentDescription = response.jsonPath().get("assignmentDescription");
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode5);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine4);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);

		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("assignmentDescription"),Constants.ExpectedAssignmentDescription);
		assertEquals(response.jsonPath().get("assignmentName"),payload.getAssignmentName());
		assertEquals(response.jsonPath().getInt("batchId"),payload.getBatchId());
		assertEquals(response.jsonPath().get("comments"),payload.getComments());
		assertEquals(response.jsonPath().get("createdBy"),payload.getCreatedBy());
		assertEquals(response.jsonPath().get("dueDate"),payload.getDueDate());
		assertEquals(response.jsonPath().get("graderId"),payload.getGraderId());
		assertEquals(response.jsonPath().get("pathAttachment1"),payload.getPathAttachment1());
		assertEquals(response.jsonPath().get("pathAttachment2"),payload.getPathAttachment2());
		assertEquals(response.jsonPath().get("pathAttachment3"),payload.getPathAttachment3());
		assertEquals(response.jsonPath().get("pathAttachment4"),payload.getPathAttachment4());
		assertEquals(response.jsonPath().get("pathAttachment5"),payload.getPathAttachment5());
		

		
	}

	@When("User sends PUT Request and request Body for Assignment Module from excel {string} and {int}")
	public void user_sends_put_request_and_request_body_for_assignment_module_from_excel_and_1(String Sheetname, Integer Rowno) throws IOException {
	    
		LoggerLoad.info("Sending the Put Request for Negative Scenario");
		getAllData(Sheetname,Rowno);
		payload.setBatchId(Integer.parseInt(Constants.Excel_Batch_ID));
		payload.setAssignmentId(Constants.Invalid_Assignment_Id);
		payload.setAssignmentDescription(Constants.AssignDesc+(faker.idNumber().hashCode()));
		
		response = given()
				.header("Content-Type",Constants.ExpectedContentType)
				.pathParam("assignmentId", Constants.Invalid_Assignment_Id)
				.body(payload)
		.when()
				.put(Constants.put_url);
		
		
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invalid assignment id for update")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invalid_assignment_id_for_update_1(Integer int1) {
	 
		LoggerLoad.info("Validating the Put Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message7);
		assertEquals(response.jsonPath().get("success"),false);
		
	}
	
	@When("User sends PUT Request and request Body for Assignment Module from excel {string} and {int} for negative")
	public void user_sends_put_request_and_request_body_for_assignment_module_from_excel_and_for_negative_1(String Sheetname, Integer Rowno) throws IOException {
	    
		LoggerLoad.info("Sending the Put Request for Negative Scenario");
		getAllData(Sheetname,Rowno);
		payload.setBatchId(Integer.parseInt(Constants.Excel_Batch_ID));
		payload.setAssignmentId(Constants.ExpectedAssignmentId);
		payload.setComments(faker.name().title());
		
		response = given()
				.header("Content-Type",Constants.ExpectedContentType)
				.pathParam("assignmentId", Constants.ExpectedAssignmentId)
				.body(payload)
		.when()
				.put(Constants.put_url);
		
		
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for missing mandatory field assignment name for update")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_missing_mandatory_field_assignment_name_for_update_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Put Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode2);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine2);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message2);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Then("User receives {int} Bad Request Status with message and boolean success details for missing mandatory field assignment description for update")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_missing_mandatory_field_assignment_description_for_update_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Put Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode2);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine2);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message3);
		assertEquals(response.jsonPath().get("success"),false);
		
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  valid Assignment Id for Assignment Module")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignment_id_for_assignment_module_1() {
	    
		LoggerLoad.info("Getting the Delete URL for Positive Scenario");
		Constants.Delete_url=getURL().getString("DELETE_By_ID");
		
	}

	@When("User sends HTTPS Request for deleting assignments by assignment id")
	public void user_sends_https_request_for_deleting_assignments_by_assignment_id_1() {
		
		LoggerLoad.info("Sending the Delete Request for Positive Scenario");
		response = given()
				.pathParam("assignmentId", Constants.ExpectedAssignmentId)
			.when()
				.delete(Constants.Delete_url);
		
	    
	}

	@Then("User receives {int} Ok status with message for valid delete request")
	public void user_receives_ok_status_with_message_for_valid_delete_request_1(Integer int1) {
		
		LoggerLoad.info("Validating the Delete Response for Positive Scenario");
		response.then().log().all();
				
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode5);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine4);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message10);
		assertEquals(response.jsonPath().get("success"),true);
	    
	}

	@When("User sends HTTPS Request for deleting assignments by invalid assignment id")
	public void user_sends_https_request_for_deleting_assignments_by_invalid_assignment_id_1() {
	   
		LoggerLoad.info("Sending the Delete Request for Negative Scenario");
		response = given()
				.pathParam("assignmentId", Constants.Invalid_Assignment_Id)
			.when()
				.delete(Constants.Delete_url);
		
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invaid delete assignment id")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invaid_delete_assignment_id_1(Integer int1) {
	    
		LoggerLoad.info("Validating the Delete Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode4);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine3);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema2));
		
		//Response body Validation
		assertEquals(response.jsonPath().get("message"),Constants.Message7);
		assertEquals(response.jsonPath().get("success"),false);
		
	}



	@When("UUser sends DELETE Request with invalid endpoint for assignment module")
	public void u_user_sends_delete_request_with_invalid_endpoint_for_assignment_module_1() {
	   
		LoggerLoad.info("Sending the Delete Request for Negative Scenario");
		response = given()
				.when()
				.delete(Constants.post_url);
		
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for invaid endpoint for assignment module")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invaid_endpoint_for_assignment_module_1(Integer int1) {
		
		LoggerLoad.info("Validating the Delete Response for Negative Scenario");
		response.then().log().all();
		
		//Status Code and Status Line Validation
		assertEquals(response.getStatusCode(),Constants.ExpectedStatusCode6);
		assertEquals(response.getStatusLine(),Constants.ExpectedStatusLine5);
		
		//Content Type Validation
		assertEquals(response.getContentType(),Constants.ExpectedContentType);
				
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(Constants.Assignment_Post_Schema3));
		
		//Response body Validation
		assertEquals(response.jsonPath().getInt("status"),Constants.ExpectedStatusCode6);
		assertEquals(response.jsonPath().get("error"),Constants.Message11);
	}
	
	public static String[][] getAllData(String Sheetname, int Rowno) throws IOException{
		
		LoggerLoad.info("Reading and Fetching the data from the Excel File");
		XLUtility xl = new XLUtility(Constants.Excelpath);
		
		int rownum = xl.getRowCount(Sheetname);
		int colcount = xl.getCellCount(Sheetname, Rowno);
		
			
		String Data [][] = new String [rownum][colcount];
		
		for (int i=Rowno ; i <= Rowno ; i++)
		{
			for (int j =0 ; j< colcount; j++)
			{
				Data[i-i][j] = xl.getCellData(Sheetname, i, j) ;
				payload = new Assignment_Payload();
				payload.setAssignmentDescription(Data[i-i][0]);
				payload.setAssignmentName(Data[i-i][1]);
				Constants.Excel_Batch_ID=Data[i-i][2];
				payload.setComments(Data[i-i][3]);
				payload.setCreatedBy(Data[i-i][4]);
				payload.setDueDate(Data[i-i][5]);
				payload.setGraderId(Data[i-i][6]);
				payload.setPathAttachment1(Data[i-i][7]);
				payload.setPathAttachment2(Data[i-i][8]);
				payload.setPathAttachment3(Data[i-i][9]);
				payload.setPathAttachment4(Data[i-i][10]);
				payload.setPathAttachment5(Data[i-i][11]);
				
			}
			
		}
		
		return Data;
	
	}


}
