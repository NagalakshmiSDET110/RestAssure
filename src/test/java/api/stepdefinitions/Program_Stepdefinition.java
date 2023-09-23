package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONObject;

import com.github.javafaker.Faker;

import api.payload.Program_Payload;
import api.utilities.Constants;
import api.utilities.LoggerLoad;
import api.utilities.XLUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class Program_Stepdefinition {
	
	
	
	public static Program_Payload payload = new Program_Payload();
	
	private static final String BASE_URL="https://lms-backend-service.herokuapp.com/lms";
	RequestSpecification request;
	String jsonString;
	String program;
	String post_url;
	String post_urlForDel;
	String get_url;
	String get_programById;
	String put_programById;
	String put_programByName;
	private static String put_programByInvalidId;
	String put_programByInvalidName;
	String Delete_ProgramById;
	String Delete_programByName;
	private static int program_id;
	private static String program_name;
	Map<String, Object>  body;
	JSONObject  jsonbody;
	Faker faker = new Faker();
	Response response;
	static String program_ID;
	static String Excelpath = "C:\\Users\\User\\eclipse-workspace\\Team12-Api-CodeWarriors\\src\\test\\resources\\testdata\\Team12-APICodeWarriors_TestData.xlsx";
	public static String FakerProgramName = "Jul23-APICodeWarrior-Salesforce-";
	public static String FakerDescription = "Salesforce Admins - ";
	public static String Status = "active";
	public static int ProgramID_Chaining;
	
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("config"); // Load properties file  // name of the properties file
		return routes;
	}

	
	@Given("User creates POST Program Request for the LMS API endpoint")
	public void user_creates_post_program_request_for_the_lms_api_endpoint() {
		LoggerLoad.info("******** Program Module Starts********");
		
		post_url = getURL().getString("POST_Program");
		System.out.println("URL for post program is "+ post_url);


	}

	@When("User sends HTTPS Request and request Body for Progam Module from excel {string} and {int}")
	public void user_sends_https_request_and_request_body_for_progam_module_from_excel_and(String Sheetname, Integer Rowno) throws IOException {
	    
		getAllData(Sheetname,Rowno);
		
		response = given()
				.header("Content-Type","application/json")
				.body(payload)
			.when()
				.post(post_url);
		 program_id = response.jsonPath().getInt("programId");
		 program_name = response.jsonPath().getString("programName");
		 System.out.println("program id " + program_id);
		 
		 payload.setProgramDescription(FakerDescription+(faker.number().hashCode()));
		 payload.setProgramName(FakerProgramName+(faker.number().hashCode()));
		 payload.setProgramStatus(Status);
		 response = given()
					.header("Content-Type","application/json")
					.body(payload)
				.when()
					.post(post_url);
		 
		 ProgramID_Chaining = response.jsonPath().getInt("programId");
	}
	

	@Then("User receives {int} Created Status with response body for Program Module")
	public void user_receives_created_status_with_response_body_for_program_module(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(),201);
		
	////Schema Validation	
//		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema("src\test\resources\ProgramSchema\Program_Post_Schema.json"));
		
		
	}
	
	@When("User sends HTTPS Request and request Body for Progam Module from excel {string} and {int} with existing program name")
	public void user_sends_https_request_and_request_body_for_progam_module_from_excel_and_with_existing_program_name(String string, Integer int1) {
	   
 //getAllData(Sheetname,Rowno);
		
		response = given()
				.header("Content-Type","application/json")
				.body(payload)
			.when()
				.post(post_url);
	
	}
	
	@Then("User receives {int} Bad Request Status with message and boolean success details for program module")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_program_module(Integer int1) {
		response.then().log().all();
		assertEquals(response.getStatusCode(),400);
	    
	}
	
	@When("User sends HTTPS Request and request Body for Progam Module from excel {string} and {int} with missing mandatory fields")
	public void user_sends_https_request_and_request_body_for_progam_module_from_excel_and_with_missing_mandatory_fields(String Sheetname, Integer Rowno) throws IOException {
	   		
		getAllData(Sheetname,Rowno);
			response = given()
				.header("Content-Type","application/json")
				.body(payload)
			.when()
				.post(post_url);
	}	
	
//For Get all Programs

	@Given("User creates GET Request for the LMS API endpoint for program module")
	public void user_creates_get_request_for_the_lms_api_endpoint_for_program_module() {
		
		get_url = getURL().getString("GET_all_Programs");
		System.out.println("URL for get all programs "+ get_url);
	   
	}

	@When("User sends HTTPS Request for getting all programs")
	public void user_sends_https_request_for_getting_all_programs() {
	   
		response = given()
				.header("Content-Type","application/json")
				.when()
				.get(get_url);
		
		
		
	}

	@Then("User receives {int} OK Status with response body for program module")
	public void user_receives_ok_status_with_response_body_for_program_module(Integer int1) {
		
	//response.then().log().all();
		assertEquals(response.getStatusCode(),200);
		
		
	}	
	
	

//Get program by Id_TC_005
	
	@Given("User creates GET Request by program id for the LMS API endpoint for program module")
	public void user_creates_get_request_by_program_id_for_the_lms_api_endpoint_for_program_module() {
		
		get_programById = getURL().getString("GET_one_ProgramId");
		System.out.println("URL for get program by id " + get_programById);	
	
	}

	@When("User sends HTTPS Request for getting program by program id")
	public void user_sends_https_request_for_getting_program_by_program_id() {
		
	//program_id = response.jsonPath().getInt("programId");
//		System.out.println("program id " + program_id);
		
		LoggerLoad.info("Sending the Get Request for getting program by program id");
		
		response = given()
				//.header("Content-Type","application/json")
				.pathParam("programId", program_id)
			.when()
				.get(get_programById);
	}	
	
	
//Get program by invalid_Id
	
	@Given("Check if user able to retrieve a program with invalid valid program ID and LMS API")
	public void check_if_user_able_to_retrieve_a_program_with_invalid_valid_program_id_and_lms_api() {
		
		get_programById = getURL().getString("GET_one_ProgramId");
		System.out.println("URL for get program by id " + get_programById);
		
	}
	
	@When("User sends HTTPS Request for getting program by program id with invalid data in program module")
	public void user_sends_https_request_for_getting_program_by_program_id_with_invalid_data_in_program_module() {
		
		response = given()
			.pathParam("programId", "1234512")
			.when()
				.get(get_programById);
		
	    
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for program module")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_program_module(Integer int1) {
		response.then().log().all();
	assertEquals(response.getStatusCode(),404);
	}	
	
//For update(put)program by id
	
	@Given("User creates PUT Request for the LMS API endpoint with Valid programID for program module")
	public void user_creates_put_request_for_the_lms_api_endpoint_with_valid_program_id_for_program_module() {
		
		put_programById = getURL().getString("PUT_Program_(update_Program_by_ID)");
		System.out.println("URL for post program is "+ put_programById);
	}

	@When("User sends HTTPS Request and request Body for Progam Module")
	public void user_sends_https_request_and_request_body_for_progam_module() {
	   
		response = given()
				.header("Content-Type","application/json")
				.pathParam("programId", program_id)
				.body(payload)
			.when()
				.put(put_programById);
	}		
	@Then("User receives {int} OK Status with updated value in response body for program module")
	public void user_receives_ok_status_with_updated_value_in_response_body_for_program_module(Integer int1) {
		LoggerLoad.info("Validating the Put Response for Positive Scenario");
		response.then().log().all();
		assertEquals(response.getStatusCode(),200);
		
	}
	
//For update(put)program by name_Posite-TC-008
	@Given("User creates PUT Request for the LMS API endpoint  and Valid program Name")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name() {
		
	 put_programByName = getURL().getString("PUT_ProgrambyName");
	 System.out.println("URL for post program is "+ put_programByName);
	}
	
	@When("User sends HTTPS Request  and  request Body with mandatory fields. \\(program Description , program Name, program status)")
	public void user_sends_https_request_and_request_body_with_mandatory_fields_program_description_program_name_program_status() {
	    
		response = given()
				.header("Content-Type","application/json")
				.pathParam("programName", program_name)
				.body(payload)
			.when()
				.put(put_programByName);
	}
	
	
	
//@ProgramModule-UpdateProgramByMissingMendatoryFields_Negative_TC_009	
@Given("User creates PUT Request for the LMS API endpoint  and Valid programID")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_id() {
	
	put_programByInvalidId= getURL().getString("PUT_Program_by_invalidID");
	System.out.println("URL for post program is "+ put_programByInvalidId);
	}

@When("User sends HTTPS Request  and request Body missing mandatory fields from excel {string} and {int}")
	public void user_sends_https_request_and_request_body_missing_mandatory_fields_from_excel_and(String Sheetname , Integer Rowno) throws IOException {
	   
	getAllData(Sheetname,Rowno);
	
	response = given()
			.header("Content-Type","application/json")
			.pathParam("programId", "program_id")
			.body(payload)
			
		.when()
			.put(put_programByInvalidId);
	}	
	
// ProgramModule-UpdateProgramByInvalidID_Negative_TC_0010	
@Given("User creates PUT Request for the LMS API endpoint  and inValid programID for program module")
public void user_creates_put_request_for_the_lms_api_endpoint_and_in_valid_program_id_for_program_module() {
	
	put_programByInvalidId= getURL().getString("PUT_Program_by_invalidID");
	System.out.println("URL for post program is "+ put_programByInvalidId);
}

@When("User sends HTTPS Request and request Body to update Progam with invalid program id from excel {string} and {int}")
public void user_sends_https_request_and_request_body_to_update_progam_with_invalid_program_id_from_excel_and(String Sheetname, Integer Rowno) throws IOException {
  
	getAllData(Sheetname,Rowno);
		response = given()
				.header("Content-Type", "application/json")
	            .header("authorization", "")
				.pathParam("programId", 1)
				
				.body(payload)
				.when()
					.put(put_programByInvalidId);
}


//@ProgramModule-UpdateProgramByinvalidprogramNameandrequestbody_Negative_TC_0011	
@Given("User creates PUT Request for the LMS API endpoint  and InValid program Name")
public void user_creates_put_request_for_the_lms_api_endpoint_and_in_valid_program_name() {
	
	put_programByInvalidName= getURL().getString("PUT_Program_by_invalidID");
	System.out.println("URL for post program is "+ put_programByInvalidName);
    
}

@When("User sends HTTPS Request  and  request Body with Invalid \\(program Description , program Name, program status)from excel {string} and {int}")
public void user_sends_https_request_and_request_body_with_invalid_program_description_program_name_program_status_from_excel_and(String Sheetname, Integer Rowno) throws IOException {
	
	getAllData(Sheetname,Rowno);

	
	response = given()
			.header("Content-Type","application/json")
			.header("Content-Type", "application/json")
            .header("authorization", "")
			.pathParam("programId", 1)
				.body(payload)
			
		.when()
			.put(put_programByInvalidName);
}

@Then("User receives {int} Bad Request Status with message and boolean success details Program")
public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
	response.then().log().all();
	assertEquals(response.getStatusCode(),400);
}


//ProgramModule-DeleteProgramByID_TC_013

@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID for program module")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id_for_program_module() {
	
	Delete_ProgramById = getURL().getString("DELETE_Program_ByProgramID");
	System.out.println("URL for Delete program by id "+ Delete_ProgramById);
}	

@When("User sends HTTPS Request for delete program by program id")
public void user_sends_https_request_for_delete_program_by_program_id() {
   
	LoggerLoad.info("Sending the Delete Request for Deleting program by program id");
	
	response = given()
			//.header("Content-Type","application/json")
			.pathParam("programId", program_id)
		.when()
			.delete(Delete_ProgramById);
}

@Then("User receives {int} Ok status with message")
public void user_receives_ok_status_with_message(Integer int1) {
	assertEquals(response.getStatusCode(),200);

}

//ProgramModule-DeleteProgramByInvalidId-Negative_TC_014

@Given("User creates DELETE Request for the LMS API endpoint  and  invalid \\{programId} for program module")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_for_program_module() {
	
	Delete_ProgramById = getURL().getString("DELETE_Program_ByProgramID");
	System.out.println("URL for Delete program by id "+ Delete_ProgramById);
}

@When("User sends HTTPS Request for delete program by invalid program id")
public void user_sends_https_request_for_delete_program_by_invalid_program_id() {
    

	LoggerLoad.info("Sending the Delete Request for Deleting program by invalid program id");
	
	response = given()
			//.header("Content-Type","application/json")
			.pathParam("programId", "12223456")
		.when()
			.delete(Delete_ProgramById);
}

@Then("User receives {int} Not Found Status with message and boolean success details for invalid program id in program module")
public void user_receives_not_found_status_with_message_and_boolean_success_details_for_invalid_program_id_in_program_module(Integer int1) {
    
	response.then().log().all();
	assertEquals(response.getStatusCode(),404);
}


//ProgramModule-DeleteProgramByProgramName-_TC_015

@Given("User ensures to perform POST operation with body as")
public void user_ensures_to_perform_post_operation_with_body_as(io.cucumber.datatable.DataTable table) {
 
	List<List<String>> data = table.asLists(String.class);
	
	body = new HashMap<String, Object>();
	body.put("programName", data.get(1).get(0));
	body.put("programDescription", data.get(1).get(1));
	body.put("programStatus", data.get(1).get(2));

	SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	String stringDate = DateFor.format(new Date());
	body.put("lastModTime", stringDate);
	body.put("creationTime", stringDate);

	jsonbody = new JSONObject(body);
}

@When("User sends the post request using {string} as")
public void user_sends_the_post_request_using_as(String url) {
	request = RestAssured.given();	
	request.header("Content-Type", "application/json");
	request.body(jsonbody.toString());
	request.baseUri(BASE_URL);

	response = request.post(url);
	program = response.getBody().jsonPath().getString("programName");
	System.out.println("Response status code: " + response.statusCode());
}

@Then("Status code should come as {int} ok")
public void status_code_should_come_as_ok(Integer int1) {
	int statusCode=((ResponseOptions<Response>) response).getStatusCode();
 	assertEquals(401, statusCode);
}

@Then("User performs Delete operation to clear the porgram for the url {string}")
public void user_performs_delete_operation_to_clear_the_porgram_for_the_url(String url) {
Response response = request.delete(url + "/" + program);
	
	if(response.statusCode() == 201) {
  	  System.out.println("Delete Program by name " + program + " has been deleted successfully.");}
}



//ProgramModule-DeleteProgramByInvalidProgramName-Negative_TC_016

@Given("User creates DELETE Request for the LMS API endpoint  and  Invalid programName for program module")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_name_for_program_module() {
	
	Delete_programByName = getURL().getString("DELETE_Program_ByProgramName");
	System.out.println("URL for Delete program by InvalidName "+ Delete_programByName);
}

@When("User sends HTTPS Request and request Body for Progam to perform delete by InvalidProgramname operation")
public void user_sends_https_request_and_request_body_for_progam_to_perform_delete_by_invalid_programname_operation() {
	
LoggerLoad.info("Sending the Delete Request for Deleting program by invalid program id");
	
	response = given()
			
			.pathParam("programName", "ProgramName")
		.when()
			.delete(Delete_programByName);
	
	System.out.println("Program Id is : "+ProgramID_Chaining);
}

@Then("User receives {int} Not Found Status with message and boolean success details Program")
public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
	LoggerLoad.info("******** Program Module Ends********");
	response.then().log().all();
	assertEquals(response.getStatusCode(),404);
}



	
public static String[][] getAllData(String Sheetname, int Rowno) throws IOException{
		
//		XLUtility xl = new XLUtility(Excelpath);
//		
//		int rownum = xl.getRowCount(Sheetname);
//		int colcount = xl.getCellCount(Sheetname, Rowno);
//		
//			
//		String petData [][] = new String [rownum][colcount];
//		
//		for (int i=1 ; i <= rownum ; i++)
//		{
//			for (int j =0 ; j< colcount; j++)
//			{
//				petData[i-1][j] = xl.getCellData(Sheetname, i, j) ;
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
				payload.setProgramName(Data[i-i][0]);
				payload.setProgramDescription(Data[i-i][1]);		
				payload.setProgramStatus(Data[i-i][2]);
			}
			
		}
		
		return Data;
	}







}
