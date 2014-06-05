package com.page5of4.here.profiles;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;
import com.page5of4.here.profiles.tests.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public abstract class ProfilesResourceSpecs {
   @Autowired
   protected ProfilesResource profilesResource;
   @Autowired
   protected ProfilesRepository profilesRepository;

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
         Profile saved = profilesRepository.getById(info.getId().toString());
         assertThat(saved).isNotNull();
         assertThat(saved.getFirstName()).isEqualTo("Jacob");
         assertThat(saved.getLastName()).isEqualTo("Lewallen");
      }
   }
}
