package api.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.github.javafaker.Faker;

import api.payload.Batch_Payload;
import api.utilities.LoggerLoad;
import api.utilities.XLUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Batch_Stepdefinition {
	static Faker faker = new Faker();
	Program_Stepdefinition Program = new Program_Stepdefinition();
	 private List<Integer> batchIds = new ArrayList<>(); 
	public static Batch_Payload payload = new Batch_Payload();
	// private List<JsonPath> jsonPaths = new ArrayList<>();
	private JsonPath jsonpath;
	private static int batchId1,programId1,batchNoOfClasses1;
	private static String batchName1,programName1,batchDescription1;
	String post_url,get_ProgramID_url,put_url,delete_url;
	String get_url,get_batchId_url,get_BatchName_url,delete_url1,delete_url2;
	static Response response,response1,response3,BatchResponse;
	static int programId;
	static String Excelpath = "C:\\Users\\User\\eclipse-workspace\\Team12-Api-CodeWarriors\\src\\test\\resources\\testdata\\Team12-APICodeWarriors_TestData.xlsx";
	 List<Response> responses = new ArrayList<>(); 
	 private static Batch_Payload payload2;
	    private static Response response2;
	 public static int BatchID_Chaining;
static String serialNumber = generateRandomSerialNumber();

public static String generateRandomSerialNumber() {
 Faker faker = new Faker();
 return faker.number().digits(3);
}

	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("config"); // Load properties file  // name of the properties file
		return routes;
	}
	public static List<Batch_Payload> getAllData(String Sheetname) throws IOException{
	 	 
		XLUtility xl = new XLUtility(Excelpath);
		int rownum = xl.getRowCount(Sheetname);
    	
    	int colcountFirstRow = xl.getCellCount(Sheetname, 1); 
    	int colcountSecondRow = xl.getCellCount(Sheetname, 2); 
  	
    	int colcount = Math.max(colcountFirstRow, colcountSecondRow);
    	String BatchData[][] = new String [rownum][colcount];
    	List<Batch_Payload> payloads = new ArrayList<>();

        for (int i = 1; i <= 2; i++) { // Loop through rows 1 and 2
            String batchDescription = xl.getCellData(Sheetname, i, 0);
            String batchName = xl.getCellData(Sheetname, i, 1) + serialNumber;
            String batchNoOfClasses = xl.getCellData(Sheetname, i, 2);
            int batchNoOfClass = Integer.parseInt(batchNoOfClasses); 
            String batchStatus = xl.getCellData(Sheetname, i, 3);
            String programIdString = xl.getCellData(Sheetname, i, 4);
            int programId = Integer.parseInt(programIdString);

            Batch_Payload payload = new Batch_Payload();
            payload.setBatchDescription(batchDescription);
            payload.setBatchName(batchName);
            payload.setBatchNoOfClasses(batchNoOfClass);
            payload.setBatchStatus(batchStatus);
            payload.setProgramId(programId);

            payloads.add(payload);
        }

        return payloads;
    }
	
	 //Batch_TC_001
	@Given("User creates POST Request for the LMS API endpoint in Batch Module")
	public void user_creates_post_request_for_the_lms_api_endpoint_in_batch_module() {
		LoggerLoad.info("******** Program Batch Module Starts********");
		post_url=getURL().getString("POST_Batch");
		
		System.out.println("PostURL of Batch Module is " + post_url);
	}

	@When("User sends HTTPS Request and  request Body for Batch Module from excel {string}")
	public  void user_sends_https_request_and_request_body_for_batch_module_from_excel_and(String Sheetname) throws IOException {
		
		List<Batch_Payload> payloads = getAllData(Sheetname);
		
		payload.setProgramId(Program_Stepdefinition.ProgramID_Chaining);
		
	        for (Batch_Payload payload : payloads) {
	            BatchResponse = given().header("Content-Type", "application/json").header("authorization", "").body(payload)
	                    .when().post(post_url);
	                    responses.add(BatchResponse);
	                   //batchId = BatchResponse.jsonPath().getInt("batchId"); // Extract batchId from each response
	                   String responseBody = BatchResponse.getBody().asString();
	                   if (responses.size() >= 2) {
	                                Response firstResponse = responses.get(0);

	                                          BatchID_Chaining = firstResponse.path("batchId");
	                                          
	                     
	                       System.out.println("Batch ID from the first response: " + BatchID_Chaining);

	                      
	                jsonpath = new JsonPath(responseBody);
	               	                batchId1 = jsonpath.getInt("batchId");
	               	                 batchName1 = jsonpath.getString("batchName");
	                 programName1 =jsonpath.getString("programName");
	                programId1= jsonpath.getInt("programId");
	                
	               batchDescription1 = jsonpath.getString("batchDescription");
	                 batchNoOfClasses1 = jsonpath.getInt("batchNoOfClasses");
	                
	                System.out.println("Batch ID: " + batchId1);
	                System.out.println("BatchName:"+ batchName1);
	               
	        }}}
	 	        
	        

	@Then("User receives {int} Created Status with response body for Batch Module.")
	public void user_receives_created_status_with_response_body_for_batch_module(Integer int1) {
		
		for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);
            LoggerLoad.info("Validating the Response");
		response.then().log().all();
		System.out.println("Response " + i + ":" + response);
		
		String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode1");
		String expectedStatusLine = getURL().getString("ExpectedStatusLine1");
		int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
		assertEquals(expectedStatusCode, response.getStatusCode());
		assertEquals(expectedStatusLine, response.getStatusLine());

//		assertEquals(response.getStatusCode(),getURL().getString("ExpectedStatusCode1"));
//		 assertEquals(getURL().getString("ExpectedStatusLine1"), response.getStatusLine());
      
		
		 
		}
}
	
	 //Batch_TC_002
@When("User sends HTTPS Request and  request Body with existing values for Batch Module from excel {string}")
public void user_sends_https_request_and_request_body_with_existing_values_for_batch_module_from_excel(String Sheetname) throws IOException {
	XLUtility xl = new XLUtility(Excelpath);
	int rownum = xl.getRowCount(Sheetname);
	int colcount = xl.getCellCount(Sheetname,3);
	String BatchData[][] = new String [rownum][colcount];
	String batchDescription = xl.getCellData(Sheetname, 3, 0);
     String batchName = xl.getCellData(Sheetname, 3, 1) + serialNumber;
     String batchNoOfClasses = xl.getCellData(Sheetname, 3, 2);
     int batchNoOfClass = Integer.parseInt(batchNoOfClasses); 
     String batchStatus = xl.getCellData(Sheetname, 3, 3);
     String programIdString = xl.getCellData(Sheetname, 3, 4);
     int programId = Integer.parseInt(programIdString);
     Batch_Payload payload1 = new Batch_Payload();
     payload1.setBatchDescription(batchDescription);
     payload1.setBatchName(batchName);
     payload1.setBatchNoOfClasses(batchNoOfClass);
     payload1.setBatchStatus(batchStatus);
     payload1.setProgramId(programId);
     response1 = given().header("Content-Type", "application/json").header("authorization", "").body(payload1)
             .when().post(post_url);
}



@Then("User receives {int} Bad Request Status with message and boolean success details")
public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
	response1.then().log().all();
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode2");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine2");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response1.getStatusCode());
	assertEquals(expectedStatusLine, response1.getStatusLine());

	 LoggerLoad.info("Validating the Response");
}

//Batch_TC_003
@When("User sends HTTPS Request and  request Body with missing fields for Batch Module from excel {string}")
public void user_sends_https_request_and_request_body_with_missing_fields_for_batch_module_from_excel(String Sheetname) throws IOException {
	XLUtility xl = new XLUtility(Excelpath);
	int rownum = xl.getRowCount(Sheetname);
	int colcount = xl.getCellCount(Sheetname,4);
	String BatchData[][] = new String [rownum][colcount];
	String batchDescription = xl.getCellData(Sheetname, 4, 0);
     String batchName = xl.getCellData(Sheetname, 4, 1) + serialNumber;
     String batchNoOfClasses = xl.getCellData(Sheetname, 4, 2);
     int batchNoOfClass = Integer.parseInt(batchNoOfClasses); 
     String batchStatus = xl.getCellData(Sheetname, 4, 3);
     String programIdString = xl.getCellData(Sheetname, 4, 4);
     int programId = Integer.parseInt(programIdString);
     Batch_Payload payload2 = new Batch_Payload();
     payload2.setBatchDescription(batchDescription);
     payload2.setBatchName(batchName);
     payload2.setBatchNoOfClasses(batchNoOfClass);
     payload2.setBatchStatus(batchStatus);
     payload2.setProgramId(programId);
     response2 = given().header("Content-Type", "application/json").header("authorization", "").body(payload2)
             .when().post(post_url);
}
@Then("User receives {int} Bad Request Status with message and boolean success details missing field")
public void user_receives_bad_request_status_with_message_and_boolean_success_details_missing_field(Integer int1) {
	response2.then().log().all();
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode2");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine2");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response2.getStatusCode());
	assertEquals(expectedStatusLine, response2.getStatusLine());

	 LoggerLoad.info("Validating the Response");
}

//GET REQUEST (All Batches)
//Batch_TC_005
@Given("User creates GET Request for the LMS API endpoint in Batch Module")
public void user_creates_get_request_for_the_lms_api_endpoint_in_batch_module() {
	get_url=getURL().getString("GET_ALL_Batches");
	
	System.out.println("GetURL of Batch Module is " + get_url);
}

@When("User sends HTTPS Request")
public void user_sends_https_request() {
   response = given().when().get(get_url);
   LoggerLoad.info("Get All Batches requested");
}

@Then("User receives {int} OK Status with response body.")
public void user_receives_ok_status_with_response_body(Integer int1) {
	response.then().log().all();
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode4");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine4");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response.getStatusCode());
	assertEquals(expectedStatusLine, response.getStatusLine());

	 
}
//GET REQUEST{ by Batch ID }
//Batch_TC_006
@Given("User creates GET Request for the LMS API endpoint with valid Batch ID")
public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id() {
    get_batchId_url = getURL().getString("GET_Batch_By_ID");
}
@When("User sends HTTPS Request with valid Batch ID")
public void user_sends_https_request_with_valid_Batch_ID() {

		 System.out.println("Batch ID: " + batchId1);
	 response = given()
             .pathParam("batchId",batchId1)
             .header("Content-Type", "application/json").header("authorization", "")
             .when()
             .get(get_batchId_url);
	   LoggerLoad.info("Get 1 Batch requested");
	    
}
//Batch_TC_007 
@Given("User creates GET Request for the LMS API endpoint with invalid Batch ID")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_batch_id() {
	get_batchId_url = getURL().getString("GET_Batch_By_ID");
}
@When("User sends HTTPS Request with invalid Batch ID")
public void user_sends_https_request_with_invalid_Batch_ID() {
   response = given().when().pathParam("batchId",000)
		   .get(get_batchId_url);
   LoggerLoad.info("Get Batch with Invalid ID requested");
}
@Then("User receives {int} Not Found Status with message and boolean success details")
public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
	response.then().log().all();
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode3");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine3");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response.getStatusCode());
	assertEquals(expectedStatusLine, response.getStatusLine());

}
//GET  REQUEST { by Batch Name}
//Batch_TC_008

@Given("User creates GET Request for the LMS API endpoint with valid Batch name")
public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_name() {
    get_BatchName_url = getURL().getString("GET_Batch_BY_NAME");
}

@When("User sends HTTPS Request with valid BATCH NAME")
public void user_sends_https_request_with_valid_BATCH_NAME() {
   response = given().pathParam("batchName",batchName1)
		   .header("Content-Type", "application/json")
		   .header("authorization", "")
		   .when()
		   .get(get_BatchName_url);
   LoggerLoad.info("Get Batch with Batch Name requested");
}
//Batch_TC_009
@Given("User creates GET Request for the LMS API endpoint with invalid Batch name")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_batch_name() {
	get_BatchName_url = getURL().getString("GET_Batch_BY_NAME");
}
@When("User sends HTTPS Request with invalid Batch name")
public void user_sends_https_request_with_invalid_Batch_name() {
   response = given().pathParam("batchName","000").when()
		   .get(get_BatchName_url);
   LoggerLoad.info("Get Batch with Invalid Batchname requested");
}
//GET  REQUEST { by program Id}   
//Batch_TC_010
@Given("User creates GET Request for the LMS API endpoint with valid Program Id")
public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_program_id() {
    get_ProgramID_url = getURL().getString("GET_Batch_BY_ProgramID");
}

@When("User sends HTTPS Request with valid Program Id")
public void user_sends_https_request_with_valid_Program_Id() {
   response = given().pathParam("programId",programId1).when()
		   .get(get_ProgramID_url);
   LoggerLoad.info("Get Batch by program ID requested");
}
//Batch_TC_011
@Given("User creates GET Request for the LMS API endpoint with invalid Program Id")
public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_program_id() {
	get_ProgramID_url = getURL().getString("GET_Batch_BY_ProgramID");
}
@When("User sends HTTPS Request with invalid Program Id")
public void user_sends_https_request_with_invalid_Program_Id() {
	//Integer programIdnull = null;
   response = given().pathParam("programId",89).when()
		   .get(get_ProgramID_url);
   LoggerLoad.info("Get Batch by Invalid programID requested");
}
//PUT REQUEST  (Update Batch by batchID)
//Batch_TC_012
@Given("User creates PUT Request for the LMS API endpoint  and Valid batch Id")
public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_batch_id() {
    put_url = getURL().getString("PUT_Batch");
}
@When("User sends HTTPS Request and request Body for Batch Module")
public void User_sends_HTTPS_Request_and_request_Body_for_Batch_Module() throws IOException {

	
     payload2 = new Batch_Payload();
  //  payload2.setBatchId(batchId1);
    payload2.setBatchDescription(batchDescription1);
    payload2.setBatchName(batchName1);
    payload2.setBatchNoOfClasses(batchNoOfClasses1);
    payload2.setBatchStatus("Active");
    payload2.setProgramId(programId1);
    payload2.setProgramName(programName1);
     
     response = given().header("Content-Type", "application/json").header("authorization", "").body(payload2)
    		 .pathParam("batchId",batchId1)
             .when().put(put_url);
     LoggerLoad.info("Updating Batch using BatchId");
}
@Then("User receives {int} OK Status with updated value in response body.")
public void user_receives_ok_status_with_updated_value_in_response_body(Integer int1) {
	response.then().log().all();
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode4");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine4");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response.getStatusCode());
	assertEquals(expectedStatusLine, response.getStatusLine());

}
//Batch_TC_012
@Given("User creates PUT Request for the LMS API endpoint  and  invalid batch ID")
public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_batch_id() {
	put_url = getURL().getString("PUT_Batch");
}
@When("User sends HTTPS Request and  request Body with invalid batchID and request for Batch Module")
public void User_sends_HTTPS_Request_and_request_Body_with_invalid_batchID_and_request_for_Batch_Module() throws IOException {
	    
	  Batch_Payload payload2 = new Batch_Payload();
	//  payload2.setBatchId(000);
     payload2.setBatchDescription(batchDescription1);
     payload2.setBatchName(batchName1);
     payload2.setBatchNoOfClasses(batchNoOfClasses1);
     payload2.setBatchStatus("Active");
     payload2.setProgramId(programId1);
     payload2.setProgramName(programName1);
   
     response = given().header("Content-Type", "application/json").header("authorization", "").body(payload2)
    		 .pathParam("batchId",000).when().put(put_url);
     LoggerLoad.info("Updating Batch using Invalid BatchId");
	    
}
//Batch_TC_013
@When("User sends HTTPS Request and request Body with valid batchID and missing mandatory fields for Batch Module")
public void user_sends_https_request_and_request_body_with_valid_batchid_and_missing_mandatory_fields_for_batch_module() throws IOException {
    String batch = null;
	  payload2 = new Batch_Payload();
	 // payload2.setBatchId(batchId1);
     payload2.setBatchDescription(batchDescription1);
     payload2.setBatchName(batch);
     payload2.setBatchNoOfClasses(batchNoOfClasses1);
     payload2.setBatchStatus("");
     payload2.setProgramId(programId1);
     payload2.setProgramName(programName1);
     
	    
     response = given().header("Content-Type", "application/json").header("authorization", "").body(payload2)
    		 .pathParam("batchId",batchId1).when().put(put_url);
     LoggerLoad.info("Updating Batch using BatchId and Missing field");
}
//DELETE REQUEST  (by Batch ID)
//@Batch_TC_014
@Given("User creates DELETE Request for the LMS API endpoint  and  valid BatchId")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_batch_id() {
	delete_url2 = getURL().getString("DELETE_Batch_ID");
}

@When("User sends HTTPS Request with valid BatchId")
public void user_sends_https_request_with_valid_batch_id() {
	response = given()
			.pathParam("batchId", batchId1)
            .header("Content-Type", "application/json")
            .header("authorization", "")
            .when()
	        .delete(delete_url2); 
	   LoggerLoad.info("Delete using Batch ID");
}
//@Batch_TC_15
@Given("user creates delete request for the LMS API endpoint and invalid batchid")
public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_batchid() {
	delete_url2 = getURL().getString("DELETE_Batch_ID");
}

@When("user sends https request with invalid batchid")
public void user_sends_https_request_with_invalid_batchid() {
	response = given()
			.pathParam("batchId", 0)
            .header("Content-Type", "application/json")
            .header("authorization", "")
            .when()
	        .delete(delete_url2); 
	   LoggerLoad.info("Delete using Batch ID");
}

@Then("user receives {int} not found status")
public void user_receives_not_found_status(Integer int1) {
	LoggerLoad.info("******** Program Batch Module Ends********");
	System.out.println("Batch Id for Chaining :" +BatchID_Chaining);
	String expectedStatusCodeStr = getURL().getString("ExpectedStatusCode3");
	String expectedStatusLine = getURL().getString("ExpectedStatusLine3");
	int expectedStatusCode = Integer.parseInt(expectedStatusCodeStr);
	assertEquals(expectedStatusCode, response.getStatusCode());
	assertEquals(expectedStatusLine, response.getStatusLine());
}

}
