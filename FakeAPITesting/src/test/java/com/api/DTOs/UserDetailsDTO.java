package com.api.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * UserDetailsDTO to represent user details
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    private NameDTO name;
    private AddressDTO address;
    @JsonProperty("phone")
    private String phone;

    // Constructors

    public UserDetailsDTO() {}

    /**
     * @param id
     * @param email
     * @param username
     * @param password
     * @param name
     * @param address
     * @param phone
     */
    public UserDetailsDTO(int id, String email, String username, String password, NameDTO name, AddressDTO address, String phone) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

  
    /**
     * Getter and setters
     * @return id,email,address,phone,name
     */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public NameDTO getName() { return name; }
    public void setName(NameDTO name) { this.name = name; }

    public AddressDTO getAddress() { return address; }
    public void setAddress(AddressDTO address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

}
