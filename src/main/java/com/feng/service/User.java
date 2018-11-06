package com.feng.service;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView(View.Platform.class)
public class User {

    @JsonView(View.Client.class)
    private Long id;
    @JsonView(View.Client.class)
    private String firstname;
    @JsonView(View.Client.class)
    private String lastname;

    private String email;

    public User(){
    }

    public User(Long id, String firstname, String lastname, String email){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

}
