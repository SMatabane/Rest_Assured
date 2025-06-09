package com.api.tests;
import com.api.filters.CustomFilters;
import com.api.utilities.JsonReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTests {

    protected static final Logger logs = Logger.getLogger(BaseTests.class);
    protected RequestSpecification request;
    private ExtentReports reports;
    protected  ExtentTest test;
    private String authToken="";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        PropertyConfigurator.configure("src/test/resources/logs/logs.properties");
    }
    @BeforeSuite
    public void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
        reports = new ExtentReports();
        reports.attachReporter(spark);
        reports.setSystemInfo("Tester", "Mankgethwa Matabane");
        reports.setSystemInfo("Environment", "QA");
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://your-api-base-url.com"; // Replace with your actual base URL
        request = RestAssured.given()
                .filter(new CustomFilters())
                .baseUri("https://favqs.com/api/")
                .header("Content-Type", "application/json")
                .header("Authorization","Token token=" + authToken);
    }

    // Serialize DTO to JSON String
    protected static String serializeDTO(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize object to JSON: " + e.getMessage());
        }
    }

    // Deserialize JSON File to DTO using JsonReader
    protected static <T> T readTestData(String path,String key, Class<T> dtoClass) {
        return JsonReader.readFromJsonFile(path,key, dtoClass);
    }

    // POST Request
    protected Response postRequest(String endpoint, Object body) {
        return request
                .body(serializeDTO(body))
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    // PUT Request
    protected Response putRequest(String endpoint, Object body,String token) {
        return request
                .header("User-Token",token)
                .body(serializeDTO(body))
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    //delete
    protected  Response deleteSession(String endpint){
        return request
                .when()
                .delete(endpint)
                .then()
                .extract()
                .response();
    }



    //get request for user session
    protected Response getRequestUserSession(String endpoint,String token) {
        return request
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // POST Request quote
    protected Response postQuote(String endpoint, Object body,String token) {
        return request
                .body(serializeDTO(body))
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }



    // Hide and mark quote as favorite Request quote
    protected Response UpdateQuotes(String endpoint,String token) {
        return request
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    //Get Activity
    protected Response getActivity(String endpoint) {
        return request
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }
    //delete Activity
    protected  Response deleteActivity(String endpint){
        return request
                .when()
                .delete(endpint)
                .then()
                .extract()
                .response();
    }


    //Follow user/unfollow
    protected Response FollowUser(String endpoint) {
        return request
                .queryParam("filter", "gose")
                .queryParam("type", "user")
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }





    @BeforeMethod
    public void setTest(Method method) {
        test = reports.createTest(method.getName());
        logs.info("Starting test: " + method.getName());
    }

    @AfterMethod
    public void tearDown() {
        reports.flush();
        logs.info("Test execution completed, Extent Report generated.");
    }
}
