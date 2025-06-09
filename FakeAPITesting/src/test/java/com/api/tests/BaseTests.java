package com.api.tests;


import com.api.DTOs.UserDTO;
import com.api.Filter.AuthenticationFilter;
import com.api.Filter.FiltersHelper;
import com.api.utilities.FileNameConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class BaseTests {

    protected  RequestSpecification request;
    protected Response response;
    private static final Logger logs=Logger.getLogger(BaseTests.class);
    @BeforeClass
    public void setUp() {
        // Configure logs
        PropertyConfigurator.configure("src/test/resources/logs.properties");

        // Set base URI
        RestAssured.baseURI = "https://fakestoreapi.com";
        logs.info("Base setup initialized.");
    }

    /**
     * @param path
     * @param u
     * @return
     */
    protected Response postAPIRequestLogin(String path, UserDTO u) {
        request= RestAssured.given().filters(new AuthenticationFilter()).body(u).contentType(ContentType.JSON);
        response= request.when()
                        .post(path)
                        .then()
                        .extract()
                        .response();
        return response;
    }

    /**
     * @param <T>
     * @param path
     * @param dtoClass
     * @return
     */
    protected <T> T getApiRequest(String  path,Class<T> dtoClass){

        try{
            String jsonSchema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA),"UTF-8");
            // Send GET request
            response = RestAssured.given()
                    .filters(new FiltersHelper()) // Custom filters
                    .when()
                    .get(path)
                    .then()
                    .assertThat()
                    .statusCode(200) // Validate status code
                    .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema)) // Validate JSON schema
                    .extract()
                    .response();



        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Deserialize response into a list of DTOs
        return response.as(dtoClass);




    }
}
