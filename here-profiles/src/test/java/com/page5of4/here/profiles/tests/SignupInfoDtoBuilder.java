package com.page5of4.here.profiles.tests;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;

import java.util.UUID;

public class SignupInfoDtoBuilder {
   private SignupInfoDto target = new SignupInfoDto();

   public static SignupInfoDtoBuilder make() {
      return new SignupInfoDtoBuilder();
   }

   public SignupInfoDtoBuilder johnDoe() {
      target.setId(UUID.randomUUID());
      target.setFirstName("John");
      target.setLastName("Doe");
      target.setEmail("jdoe@test.com");
      target.setPassword("asdfasdf");
      return this;
   }

   public SignupInfoDto build() {
      return target;
   }
}
