package com.api.DTos.users;


public class UserReqestDTO {

    private UserDTO user;


    // Constructors
    public UserReqestDTO() {}

    public UserReqestDTO(UserDTO user) {
        this.user = user;
    }

    // Getter and Setter
    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "user=" + user +
                '}';
    }
}
