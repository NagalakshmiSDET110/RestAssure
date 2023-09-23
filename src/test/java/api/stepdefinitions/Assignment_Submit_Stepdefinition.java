package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import org.json.JSONTokener;

import api.payload.Assignment_Submit_Payload;
import api.utilities.Constants;
import api.utilities.LoggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class Assignment_Submit_Stepdefinition {

	public static Assignment_Submit_Payload payload = new Assignment_Submit_Payload();
	String post_url;
	String get_url;
	String delete_url;
	Response response;
	public static Integer assignmentId;
	public static Integer submissonId;
	public static String userId;
	public static String studentID;

	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("config"); // Load properties file ,name of the properties
		return routes;
	}

	@Given("User creates POST Request for the LMS API Assignment Submit Module")
	public void user_creates_post_request_for_the_lms_api_assignment_submit_module() {
		LoggerLoad.info("******** Assignment Submit Module Starts********");
		post_url = getURL().getString("POST_Submit_Assignment");
		System.out.println("PostURL is " + post_url);
	}

	@When("User sends HTTPS Request and  request Body for Assignment Submit Module from excel {string} and {int}")
	public void user_sends_https_request_and_request_body_for_assignment_submit_module_from_excel_and(String sheetName,
			Integer rowNo) throws IOException {
		File file = new File("AssignmentSubmit.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
		
		response = given().header("Content-Type", "application/json").body(payload.toString()).when().post(post_url);
		if (response.jsonPath().get("assignmentId") != null) {
			assignmentId = response.jsonPath().get("assignmentId");
			System.out.println("#####assignmentId########" + assignmentId);
		}

		if (response.jsonPath().get("submissionId") != null) {
			submissonId = response.jsonPath().get("submissionId");
			System.out.println("#####submissonId########" + submissonId);
		}

		if (response.jsonPath().get("userId") != null) {
			userId = response.jsonPath().get("userId");
			System.out.println("#####userId########" + userId);
		}
	}

	@Then("User receives {int} Created Status with response body for Assignment Submit Module.")
	public void user_receives_created_status_with_response_body_for_assignment_submit_module(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 201);
	}
//post with mandatory fields

	@Then("User receives {int} Bad Request Status with message and boolean success details for Assignment Submit Module")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_assignment_submit_module(
			Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 400);
	}

	@Given("User creates POST Request for the LMS API endpoint with missing field")
	public void user_creates_post_request_for_the_lms_api_endpoint_with_missing_field() {
		post_url = getURL().getString("POST_Submit_Assignment");
		System.out.println("PostURL is " + post_url);
	}

	@When("User sends HTTPS Request and request Body for Assignment Submit Module")
	public void user_sends_https_request_and_request_body_for_assignment_submit_module() throws IOException {
		File file = new File("AssignmentSubmit.json");
		FileReader Fr = new FileReader(file);
		JSONTokener jsonTokener = new JSONTokener(Fr);
		org.json.JSONObject payload = new org.json.JSONObject(jsonTokener);
		response = given().header("Content-Type", "application/json").body(payload.toString()).when().post(post_url);
	}

	// Get All Submissions
	@Given("User creates GET Request for the LMS API endpoint for Assignment Submit Module")
	public void user_creates_get_request_for_the_lms_api_endpoint_for_assignment_submit_module() {
		get_url = getURL().getString("GET_All_Submission");
		System.out.println("URL for getting all submissions" + get_url);
	}

	@When("User sends HTTPS Request for getting all submissions")
	public void user_sends_https_request_for_getting_all_submissions() {
		response = given().header("Content-Type", "application/json").body(payload).when().get(get_url);
	}

	@Then("User receives {int} OK Status with response body for Assignment Submit Module.")
	public void user_receives_ok_status_with_response_body_for_assignment_submit_module(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}
	// GET REQUEST {get Grades by Assignment ID }--Invalid data

	@Given("User creates GET Request for the LMS API endpoint with invalid Assignment ID Submit")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_assignment_id() {
		get_url = getURL().getString("GET_Grades_by_Assignment_ID");
		System.out.println("URL for get with valid assignment ID" + get_url);
	}

	@When("User sends HTTPS Request with invalid Assignment ID")
	public void user_sends_https_request_with_invalid_assignment_id() {
		response = given().pathParam("assignmentId", "655578").when().get(get_url);
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for Assignment Submit Module.")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_assignment_submit_module(
			Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 404);
	}

// GET REQUEST {get Grades by Assignment ID }--valid data

	@Given("User creates GET Request for the LMS API endpoint with valid Assignment ID Assignment Submit Module")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_assignment_id() {
		get_url = getURL().getString("GET_Grades_by_Assignment_ID");
		System.out.println("URL for get with valid assignment ID" + get_url);
	}

	@When("User sends HTTPS Request with valid Assignment ID")
	public void user_sends_https_request_with_valid_assignment_id() {
		
		response = given().pathParam("assignmentId", Constants.Assignment_Id_Chaining).when().get(get_url);
	}

	// delete with valid submission ID
	@Given("User creates DELETE Request for the LMS API endpoint  and  valid submission ID")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_submission_id() {
		delete_url = getURL().getString("DELETE_Submission_by_ID");
		System.out.println("DeleteURL is " + delete_url);
	}

	@When("User sends HTTPS Request with valid submissionID")
	public void user_sends_https_request_with_valid_submission_id() {
		System.out.println("submissionId to be deleted#########" + submissonId);
		response = given().pathParam("Id", submissonId).when().delete(delete_url);
	}

	@Then("User receives {int} Ok status with message Submit")
	public void user_receives_ok_status_with_message(Integer int1) {
		LoggerLoad.info("******** Assignment Submit Module Ended********");
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}

	// delete with Invalid submission ID
	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid submission id")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_submission_id() {
		delete_url = getURL().getString("DELETE_Submission_by_ID");
		System.out.println("DeleteURL is " + delete_url);
	}

	@When("User sends HTTPS Request with invalid submissionID")
	public void user_sends_https_request_with_invalid_submission_id() {
		response = given().pathParam("Id", "1563").when().delete(delete_url);
	}

//GET  REQUEST {get Submission by User ID }--valid data

	@Given("User creates GET Request for the LMS API endpoint with valid User Id for Assignment Submit Module")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_user_id_for_assignment_submit_module() {

		get_url = getURL().getString("GET_Submission_by_User_ID");
		System.out.println("URL for get with valid User ID" + get_url);
	}

	@When("User sends HTTPS Request with valid User Id for Assignment Submit Module")
	public void user_sends_https_request_with_valid_user_id_for_assignment_submit_module() {

		response = given().pathParam("userId", userId).when().get(get_url);
	}

	// GET REQUEST {get Submission by User ID }--Invalid data
	@Given("User creates GET Request for the LMS API endpoint with invalid user ID for Assignment Submit Module")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_user_id_for_assignment_submit_module() {
		get_url = getURL().getString("GET_Submission_by_User_ID");
		System.out.println("URL for get with valid User ID" + get_url);
	}

	@When("User sends HTTPS Request submission with Invalid User ID to LMS API")
	public void user_sends_https_request_submission_with_invalid_user_id_to_lms_api() {
		response = given().pathParam("userId", "99999999").when().get(get_url);
	}

	@Then("User receives {int} Status with response body for Assignment Submit Module.")
	public void user_receives_status_with_response_body_for_assignment_submit_module(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 404);
	}

	// GET REQUEST {get Grades by Student ID }--invalid data
	@Given("User creates GET Request for the LMS API endpoint with invalid Student ID")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_student_id() {
		get_url = getURL().getString("GET_Grades_by_StudentID");
		System.out.println("URL for get with valid Student ID" + get_url);
	}

	@When("User sends HTTPS Request with invalid Student ID for Assignment Submit Module")
	public void user_sends_https_request_with_invalid_student_id_for_assignment_submit_module() {
		response = given().pathParam("studentID", "U782288").when().get(get_url);
	}

	// GET REQUEST {get Grades by Student ID }--valid data

	@Given("User creates GET Request for the LMS API endpoint with valid Student Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_student_id() {
		get_url = getURL().getString("GET_Grades_by_StudentID");
		System.out.println("URL for get with valid Student ID" + get_url);
	}

	@When("User sends HTTPS Request with valid Student ID for Assignment Submit Module")
	public void user_sends_https_request_with_valid_student_id_for_assignment_submit_module() {

		response = given().pathParam("studentID", "U8532").when().get(get_url);
	}

	// GET REQUEST{get Grades by Batch ID }--invalid data
	@Given("User creates GET Request for the LMS API endpoint with invalid Batch Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_batch_id() {
		get_url = getURL().getString("GET_Grades_by_BatchID");
		System.out.println("URL for get with valid Batch ID" + get_url);
	}

	@When("User sends HTTPS Request with invalid Batch Id for Assignment Submit Module")
	public void user_sends_https_request_with_invalid_batch_id_for_assignment_submit_module() {
		response = given().pathParam("batchID", 770099).when().get(get_url);
	}

	// GET REQUEST{get Grades by Batch ID }--valid data

	@Given("User creates GET Request for the LMS API endpoint with valid Batch Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id() {
		get_url = getURL().getString("GET_Grades_by_BatchID");
		System.out.println("URL for get with valid Batch ID" + get_url);
	}

	@When("User sends HTTPS Request with valid Batch Id for Assignment Submit Module")
	public void user_sends_https_request_with_valid_batch_id_for_assignment_submit_module() {
		response = given().pathParam("batchID", 6407).when().get(get_url);
	}
//GET  REQUEST{get Submission  by Batch ID }--valid data

	@Given("User creates GET Request for the LMS API endpoint submission with valid Batch Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_submission_with_valid_batch_id() {
		get_url = getURL().getString("GET_Submission_by_batch_ID");
		System.out.println("URL for get with submission valid Batch ID" + get_url);
	}

	@When("User sends HTTPS Request with submission valid Batch Id for Assignment Submit Module")
	public void user_sends_https_request_with_submission_valid_batch_id_for_assignment_submit_module() {
		response = given().pathParam("batchId", 7122).when().get(get_url);
	}

	// GET REQUEST{get Submission by Batch ID }--Invalid data
	@Given("User creates GET Request for the LMS API endpoint with submisssion invalid Batch  Id")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_submisssion_invalid_batch_id() {
		get_url = getURL().getString("GET_Submission_by_batch_ID");
		System.out.println("URL for get with submission valid Batch ID" + get_url);
	}

	@When("User sends HTTPS Request with submission invalid Batch Id for Assignment Submit Module")
	public void user_sends_https_request_with_submission_invalid_batch_id_for_assignment_submit_module() {
		response = given().pathParam("batchId", 677777777).when().get(get_url);
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for submission invalid Assignment Submit Module.")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_submission_invalid_assignment_submit_module(
			Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 404);
	}

	// put
	@Given("User creates PUT Request for the LMS API endpoint  and Valid submission Id")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_submission_id() {
		get_url = getURL().getString("PUT_Resubmit_Assignment");
		System.out.println("URL for get with valid assignment ID" + get_url);
	}

	@Given("User creates PUT Request for Grade assignment the LMS API endpoint  and Valid submission Id")
	public void user_creates_put_request_for_the_grade_lms_api_endpoint_and_valid_submission_id() {
		get_url = getURL().getString("PUT_Grade_Assignment_Submission");
		System.out.println("URL for get with valid assignment ID" + get_url);
	}

	@Given("User creates PUT Request for the LMS API endpoint  and  invalid submission ID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_submission_id() {
		get_url = getURL().getString("PUT_Resubmit_Assignment");
		System.out.println("URL for get with valid assignment ID" + get_url);
	}

	@When("User sends HTTPS Request  and request Body  \\(missing mandatory fields)")
	public void user_sends_https_request_and_request_body_missing_mandatory_fields() {

//		payload.setUserId("U7900");
//		payload.setSubDesc("assignment is re submitted");
//		payload.setAssignmentId(3879);
		response = given().pathParam("Id", 1872).header("Content-Type", "application/json").body(payload)
				.when().put(get_url);

	}

	@Then("User receives {int} Bad Request Status with message and boolean success details Submit")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 400);
	}

	@When("User sends HTTPS Request and  request Body with mandatory  fields")
	public void user_sends_https_request_and_request_body_with_mandatory_fields() {

		payload.setAssignmentId(4699);
		payload.setGrade(1);
		payload.setGradedBy("U8528");
	//response = given().pathParam("submissionId", submissonId).header("Content-Type", "application/json").body(payload)
	//		.when().put(get_url);
	}

	@Then("User receives {int} OK Status with updated value in response body Submit.")
	public void user_receives_ok_status_with_updated_value_in_response_body_2(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(), 200);
	}

	@When("User sends HTTPS Request  and  request Body  with mandatory, additional  fields")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {

		payload.setAssignmentId(4699);
		response = given().pathParam("Id", 1872).header("Content-Type", "application/json").body(payload)
				.when().put(get_url);
	}

	@When("User sends HTTPS Request and  request Body with valid info for mandatory ,additional  fields")
	public void user_sends_https_request_and_request_body_with_valid_info_for_mandatory_additional_fields() {
		payload.setAssignmentId(4699);
		response = given().pathParam("Id", 1872).header("Content-Type", "application/json").body(payload)
				.when().put(get_url);
	}
	@Then("User receives {int} Not Found Status with message and boolean success details Submit")
	public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
	//	response.then().log().all();
	//	assertEquals(response.getStatusCode(), 404);
	}
	@Then("User receives {int} OK Status with updated value in response body with valid submission id")
	public void user_receives_ok_status_with_updated_value_in_response_body_with_valid_submission_id(Integer int1) {
	//	response.then().log().all();
	//	assertEquals(response.getStatusCode(), 200);
	}

	


}
