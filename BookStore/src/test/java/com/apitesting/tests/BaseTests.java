package com.apitesting.tests;


import com.apitesting.utils.ExtentReport;
import com.apitesting.utils.RestAssuredFilters;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.testng.Assert.assertTrue;

public class BaseTests {

    protected static final Logger logs = Logger.getLogger(BaseTests.class);
    protected static final String url = "https://demoqa.com";
    protected static RequestSpecification request;
    protected ExtentTest test;
    protected static ObjectMapper mapper;


    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @BeforeSuite
    public void setUpSuite() {
        // Initializing Extent
        PropertyConfigurator.configure("src/test/resources/logs/logs.properties");
        ExtentReport.setUpReports();
        logs.info("Extent Report initialized");
    }


    @BeforeClass
    public void setUpClass() {

        request = RestAssured.given()
                .filter(new RestAssuredFilters())
                .baseUri(url)
                .header("Content-type", "application/json");

    }


    @BeforeMethod
    public void startTest(Method method) {

        ExtentTest extentTest = ExtentReport.getExtent().createTest(method.getName());
        ExtentReport.setTest(extentTest);
        this.test = extentTest;

        logs.info("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentReport.getTest().log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.fail("test failed: " + result.getTestName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped: " + result.getTestContext());
        }

        logs.info("Completed test: " + result.getMethod().getMethodName());
        ExtentReport.flushReport();
    }

    protected String convertToJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    protected <T> T readJson(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(jsonString, clazz);
    }

    //post request
    public Response postAPIRequest(String uri, String requestBody, int... status) {

        Response response = request
                .body(requestBody).when().post(uri);

        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = response.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }

        return response;

    }

    public Response getAPIRequest(String uri, String token, int... status) {
        Response response = request.header("Authorization", "Bearer " + token).when().get(uri);


        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = response.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return response;
    }


    public Response deleteAPIRequest(String uri, String token, int... status) {
        Response resp = request.header("Authorization", "Bearer " + token).when().delete(uri);

        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = resp.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return resp;
    }


    public Response getAPIRequest(String uri, int... status) {
        Response response = request.when().get(uri);


        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = response.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return response;
    }


    /**
     * get request with query params
     * @param uri
     * @param query
     * @param status
     * @return
     */
    public Response getRequest(String uri,String query, int... status) {
        Response response = RestAssured
                .given()
                .baseUri(url)
                .header("Content-type", "application/json")
                .queryParam("ISBN",query).when().get(uri);


        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = response.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return response;
    }

    /**
     * post request
     * @param uri
     * @param token
     * @param requestBody
     * @param status
     * @return
     */
    public Response postAPIRequest( String uri,String token,String requestBody,int... status) {

        Response response= request.header("Authorization", "Bearer " + token)
                .body(requestBody).when().post(uri);

        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = response.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }

        return response;

    }

    /**
     * delete request with query params
     * @param uri
     * @param params
     * @param token
     * @param status
     * @return
     */

    public Response deleteRequest(String uri,String params, String token, int... status) {
        Response resp = request.header("Authorization", "Bearer " + token)
                .queryParam("UserId", params)
                .when()
                .delete(uri);

        // Validate status codes
        if (status != null && status.length > 0) {
            int statusCode = resp.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return resp;
    }

    /**
     * edit request for updating books
     * @param uri
     * @param body
     * @param token
     * @param status
     * @return
     */
    public Response editBook(String uri,String body,String token,int...status){
        Response resp=request.header("Authorization", "Bearer " + token)
                .body(body).when().put(uri);

        if (status != null && status.length > 0) {
            int statusCode = resp.getStatusCode();
            boolean validStatus = Arrays.stream(status).anyMatch(code -> code == statusCode);
            assertTrue(validStatus,
                    "Expected status codes " + Arrays.toString(status) + " but got: " + statusCode);
        }
        return resp;
    }


}
