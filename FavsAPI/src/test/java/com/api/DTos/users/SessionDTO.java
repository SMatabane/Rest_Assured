package com.api.DTos.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDTO {

    private UserDTO user;

    // Default constructor
    public SessionDTO() {}

    public SessionDTO(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }


}


