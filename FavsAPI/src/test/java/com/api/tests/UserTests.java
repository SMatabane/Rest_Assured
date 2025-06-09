package com.api.tests;

import com.api.DTos.quotes.QuoteRequest;
import com.api.DTos.users.SessionDTO;
import com.api.DTos.users.UpdateDTo;
import com.api.DTos.users.UserReqestDTO;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserTests extends BaseTests{


    private static final String USER_JSON_PATH = "src/test/resources/TestData/user.json";
    private static final String QUOTE_JSON_PATH = "src/test/resources/TestData/quotes.json";
    private final Logger logs = Logger.getLogger(UserTests.class);
    private static String token;
    private static Response response;
    private static String username;


    //create user
    @Test(priority = 1,enabled = false)
    public void createUser(){
        String register_path="/users";
       // Read user data from JSON file
        UserReqestDTO newUser =readTestData(USER_JSON_PATH,"register",UserReqestDTO.class);
         response=postRequest(register_path,newUser);


        // Validate response
        assertNotNull(response, "Response should not be null");
        assertEquals(response.getStatusCode(),200);
        test.info(" User registered successfully. Token:" + "status code " + response.getStatusCode());
        logs.info(" User registered successfully. Token: ");

    }
    //create user session
    @Test(priority = 2)
    public void sessionCreate(){
        String login_path="/session";
        // Read user data from JSON file
        SessionDTO user =readTestData(USER_JSON_PATH,"session", SessionDTO.class);
         response=postRequest(login_path,user);

        token=response.jsonPath().get("User-Token").toString();
        username=response.jsonPath().getString("login");
        assertEquals(response.getStatusCode(),200);
        assertFalse(token.isEmpty(), "Token should not be null");
        test.info("Test passed with status code" + response.getStatusCode());

    }

    //update user
    @Test(dependsOnMethods = {"sessionCreate"},priority = 3)
    public void updateUser(){
        String getuser_path="users/" + username;
        UpdateDTo user=readTestData(USER_JSON_PATH,"updateUser", UpdateDTo.class);
        response=putRequest(getuser_path,user,token);

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.jsonPath().getString("message"),"User successfully updated.");
        test.info("Test passed" + response.getStatusCode() + "\n"+ response.jsonPath().getString("message"));


    }


    //get user details
    @Test(dependsOnMethods = {"sessionCreate"},priority = 4)
    public void getUsersDetailsSession(){
        String getuser_path="users/" + username;
         response=getRequestUserSession(getuser_path,token);

        assertEquals(response.getStatusCode(),200);
        test.info("User Details: " + response.getBody());
        test.info("Test passed" + response.getStatusCode());
        logs.info("User Details: " + response.getBody());

    }

    //add quotes
    @Test(dependsOnMethods = {"sessionCreate"},priority = 5)
    public void addQuotes(){
        String addQuote="/quotes";
        QuoteRequest quote=readTestData(QUOTE_JSON_PATH,"addQuotes", QuoteRequest.class);
        response=postQuote(addQuote,quote,token);

        String author=response.jsonPath().getString("author");

        assertEquals(response.getStatusCode(),200);
        assertEquals(author,"State Trooper");
        test.info("Test passed " + response.getStatusCode());
        logs.info("Test passed " +response.getStatusCode());



    }


    //hide quotes
    @Test(dependsOnMethods = {"sessionCreate"},priority = 6)
    public void HideQuotes(){
        int id=4;
        String addQuote="/quotes/" + id+ "/hide";
        response=UpdateQuotes(addQuote,token);

        Boolean hidden = response.jsonPath().getBoolean("user_details.hidden");

        assertEquals(response.getStatusCode(),200);
        assertEquals(hidden,true);
        test.info("Test passed " + response.getStatusCode());
        logs.info("Test passed " +response.getStatusCode());



    }

    //mark quote as favourite
    @Test(dependsOnMethods = {"sessionCreate"},priority = 7)
    public void FavouriteQuotes(){
        int id=35;
        String addQuote="/quotes/" + id+ "/fav";
        response=UpdateQuotes(addQuote,token);
        Boolean favorite = response.jsonPath().getBoolean("user_details.favorite");

        assertEquals(response.getStatusCode(),200);
        assertEquals(favorite,true);
        test.info("Test passed " + response.getStatusCode());
        logs.info("Test passed " +response.getStatusCode());

    }

    //get activity
    @Test(priority = 8)
    public void getActivity(){
        String activity_Uri="/activities";
        response=getActivity(activity_Uri);

      String name = response.jsonPath().getString("activities[0].owner_value");

        assertEquals(response.getStatusCode(),200);
        assertEquals(name,"nele_22");
        test.info("Test passed " + response.getStatusCode() +response.getBody());

    }

    //delete activity
    @Test(priority = 9,dependsOnMethods = {"getActivity"})
    public void DeleteActivity(){
        int actId=response.jsonPath().getInt("activity[0].activity_id");
        String activity_Uri="/activities/" + actId;
        response=deleteActivity(activity_Uri);

        assertEquals(response.getStatusCode(),200);
        assertTrue(response.getBody().asString().isEmpty()); //assert the body is empty

        test.info("Test passed " + response.getStatusCode() + "Deleted activity successfully");
        logs.info("Deleted activity successfully");
    }

    //follow user
    @Test(priority = 10)
    public void Follow(){
        String activity_Uri="activities/follow/";
        response=FollowUser(activity_Uri);

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.jsonPath().getString("status"),"ok");

        test.info("Test passed " + response.getStatusCode() + "followed user successfully");
        logs.info("Unfollowed user successfully");

    }

    //UNfollow user
    @Test(priority = 11)
    public void UNFollow(){
        String activity_Uri="activities/unfollow";
        response=FollowUser(activity_Uri);

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.jsonPath().getString("status"),"ok");
        test.info("Test passed " + response.getStatusCode() + "Unfollowed user successfully");
        logs.info("Unfollowed user successfully");
    }

    //Delete user session
    @Test(priority = 12)
    public void destroySession(){
        String session_destroy="/session";
         response=deleteSession(session_destroy);

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.jsonPath().get("message"),"User logged out.");
        test.info("Test passed with message: " + response.jsonPath().getString("message"));
        logs.info("User deleted sucecessfully: " + response.getBody());


    }




}
