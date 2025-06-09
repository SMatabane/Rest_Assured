package com.api.tests;



import com.api.DTOs.UserDTO;
import com.api.DTOs.UserDetailsDTO;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FakeApiTests extends BaseTests{
    private static final Logger logs=Logger.getLogger(FakeApiTests.class);

    /**
     * Test login with incorrect credentials
     */
    @Test(priority=1,enabled = true)
    public void InvaliduserLogin(){
        // Create UserDTO with credentials
        UserDTO user = new UserDTO("testUser", "testPassword");
        assertEquals(postAPIRequestLogin("/auth/login",user).getStatusCode(),401);
    }

    /**
     * Test login with correct credentials
     */
    @Test(priority=2,enabled = true)
    public void ValiduserLogin(){
        // Create UserDTO with credentials
        UserDTO user = new UserDTO("mor_2314", "83r5^_");
        assertEquals(postAPIRequestLogin("/auth/login",user).getStatusCode(),200);
    }

    /**
     * The method which gets all users and validates the status code
     */
    @Test(priority = 3,enabled = false)
    public void getUsers(){
        UserDetailsDTO user =getApiRequest("/users/1",UserDetailsDTO.class); //get users

            logs.info("User ID: " + user.getId());
            logs.info("Username: " + user.getUsername());
            logs.info("Email: " + user.getEmail());
            logs.info("Adress: " + user.getAddress().getGeolocation());
            logs.info("----------------------");



    }
    }

