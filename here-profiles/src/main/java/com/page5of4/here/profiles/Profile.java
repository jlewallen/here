package com.page5of4.here.profiles;

import java.util.UUID;

public class Profile {
   private UUID id;
   private String firstName;
   private String lastName;
   private String email;
   private String password;

   public UUID getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public String getEmail() {
      return email;
   }

   public String getPassword() {
      return password;
   }

   public Profile(UUID id, String firstName, String lastName, String email, String password) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
   }
}
