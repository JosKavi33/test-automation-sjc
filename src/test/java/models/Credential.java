package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credential {
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;


    public Credential(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Credential() {
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
