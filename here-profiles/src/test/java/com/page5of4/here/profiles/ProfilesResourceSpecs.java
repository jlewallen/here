package com.page5of4.here.profiles;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;
import com.page5of4.here.profiles.tests.ProfilesSpecsModule;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfilesResourceSpecs {
   protected ProfilesResource profilesResource;
   protected ProfilesRepository repository;

   @Before
   public void setup() {
      ProfilesSpecsModule module = new ProfilesSpecsModule();
      repository = module.profilesRepository(module.dbi());
      profilesResource = module.profilesResource();
   }

   public static class when_signing_up extends ProfilesResourceSpecs {
      private SignupInfoDto info;

      @Before
      public void before() {
         info = new SignupInfoDto();
         info.setId(UUID.randomUUID());
         info.setFirstName("Jacob");
         info.setLastName("Lewallen");
         info.setEmail("test@test.com");
         info.setPassword("asdfasdf");
         profilesResource.signup(info);
      }

      @Test
      public void should_add_the_user_and_they_should_be_retrievable() {
         Profile saved = repository.getById(info.getId().toString());
         assertThat(saved).isNotNull();
         assertThat(saved.getFirstName()).isEqualTo("Jacob");
         assertThat(saved.getLastName()).isEqualTo("Lewallen");
      }
   }
}
