package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class User implements Serializable {

   private String username;

   private Boolean enabled;

   private String firstName;

   private String lastName;

   private List<String> realmRoles;

   private String email;

   private Boolean emailVerified;

   private List<Credential> credentials;
}
