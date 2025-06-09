package com.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    /**
     * @param username
     * @param password
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    /**
     *  Getters & Setters
     * @return
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;


    }
}
