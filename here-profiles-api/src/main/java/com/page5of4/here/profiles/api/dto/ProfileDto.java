package com.page5of4.here.profiles.api.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ProfileDto {
   private UUID id;
   @NotNull
   private String firstName;
   @NotNull
   private String lastName;
   @NotNull
   private String email;

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}
