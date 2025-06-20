package com.apitesting.tests;

import com.apitesting.dataproviders.JsonReader;
import com.apitesting.models.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AccountTest extends BaseTests {


    private static String userId;
    private static String token;


    /**
     * Test: creating new user
     * @param data
     */
    @Test(priority = 1,dataProvider = "accountUser", dataProviderClass = JsonReader.class)
    public void testCreateUser(JsonNode data) throws JsonProcessingException {
        Account acc = new Account(data.get("userName").asText(), data.get("password").asText());
        String requestBody = convertToJson(acc);
        Response resp;

        if (data.get("type").asText().equalsIgnoreCase("valid")) {
            resp = postAPIRequest("/Account/v1/User", requestBody, 201);
            userId = resp.jsonPath().getString("userID");
            logs.info("Created user successfully! ");
            test.pass("Created user successfully");
        } else if (data.get("type").asText().equalsIgnoreCase("invalid")) {
            resp = postAPIRequest("/Account/v1/User", requestBody, 400,404,406);
            logs.info("Received expected error status code: " + resp.getStatusCode());
            test.pass("Received expected error status code: " + resp.getStatusCode());
        }


    }

    /**
     * Test: Generate user token
     * @param data
     * @throws JsonProcessingException
     */

    @Test(priority = 2,dataProvider = "accountUser", dataProviderClass = JsonReader.class)
    public void testGenerateToken(JsonNode data) throws JsonProcessingException {

        Account acc = new Account(data.get("userName").asText(), data.get("password").asText());

        String requestBody = convertToJson(acc);
        Response resp;
        if (data.get("type").asText().equalsIgnoreCase("valid")) {
            resp = postAPIRequest("Account/v1/GenerateToken", requestBody, 200);
            String status = resp.jsonPath().getString("status");
            String resultMessage = resp.jsonPath().getString("result");
            token=resp.jsonPath().getString("token");
            // Assert status is "Success"
           assertEquals(status, "Success", "Expected status to be 'Success' but got: " + status + " - " + resultMessage);

            logs.info("Token generation status: " + status + " | Message: " + resultMessage);
            test.pass("Token generated successfully.");
        } else if (data.get("type").asText().equalsIgnoreCase("invalid")) {
            resp = postAPIRequest("Account/v1/GenerateToken", requestBody, 200);
            String status = resp.jsonPath().getString("status");
            String resultMessage = resp.jsonPath().getString("result");

            // Assert status is "Success"
            assertEquals(status, "Failed", "Expected status to be 'Failed' but got: " + status + " - " + resultMessage);
            logs.info("Received expected  error results: " + resp.getStatusCode());
            test.pass("Received expected error results: " + resp.getStatusCode());
        }

    }

    /**
     * Test: get user
     */
    @Test(priority = 3)
    public void testGetUser() {

        String uri = "/Account/v1/User/" + userId;
        Response response = getAPIRequest(uri,token,200);

        String fetchedUserId = response.jsonPath().getString("userId");
        assertEquals(fetchedUserId, userId, "Fetched userId does not match created userId!");
        logs.info("Fetched user details successfully " );
        test.pass("Fetched user details successfully: " );

    }

    /**
     * Test; Delete user
     */
    @Test(priority = 4)
    public void deleteUser(){
        String uri = "/Account/v1/User/" + userId;

       Response resp= deleteAPIRequest(uri,token,204);
        String message= resp.jsonPath().getString("message");
        assertEquals(message,"User deleted");

        logs.info("Deleted  user successfully: " );
        test.pass("Deleted  user successfully " );

    }



    }






