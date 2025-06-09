package com.api.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameDTO {
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;

    // Constructors
    public NameDTO() {}

    /**
     * @param firstname
     * @param lastname
     */
    public NameDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // 
    /**
     * Getters and Setters
     * @return firstname and lastname
     */
    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
}
