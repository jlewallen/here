package com.page5of4.here.profiles;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.dto.SignupInfoDto;
import com.page5of4.here.profiles.tests.ProfilesDeps;
import com.page5of4.here.profiles.tests.SignupInfoDtoBuilder;
import com.page5of4.here.tests.UrlParts;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfilesResourceJerseySpecs {
   @ClassRule
   public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(ProfilesDeps.get().getBean(ProfilesResource.class)).build();

   private static Builder profiles(Object... parts) {
      return resources.client().resource(UrlParts.join("profiles").join(parts).url()).type(MediaType.APPLICATION_JSON_TYPE);
   }

   @Before
   public void before() {
      resources.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
   }

   public static class get_profiles_after_startup extends ProfilesResourceJerseySpecs {
      @Test
      public void should_return_a_collection() {
         List<ProfileDto> profiles = profiles().get(ProfilesArray);
         assertThat(profiles).isNotNull();
      }
   }

   public static class add_multiple_and_get_all_profiles extends ProfilesResourceJerseySpecs {
      @Before
      public void before() {
         super.before();

         profiles().post(SignupInfoDtoBuilder.make().johnDoe().build());
         profiles().post(SignupInfoDtoBuilder.make().johnDoe().build());
      }

      @Test
      public void should_not_be_empty() {
         assertThat(profiles().get(ProfilesArray)).isNotEmpty();
      }
   }

   public static class add_and_delete_and_get_profiles extends ProfilesResourceJerseySpecs {
      @Before
      public void before() {
         super.before();

         SignupInfoDto signupDto = SignupInfoDtoBuilder.make().johnDoe().build();

         profiles().post(signupDto);
         profiles().delete();
      }

      @Test
      public void should_be_empty() {
         assertThat(profiles().get(ProfilesArray)).isEmpty();
      }
   }

   public static class add_and_get_profile extends ProfilesResourceJerseySpecs {
      private SignupInfoDto signupDto;

      @Before
      public void before() {
         super.before();

         signupDto = SignupInfoDtoBuilder.make().johnDoe().build();

         profiles().post(signupDto);
      }

      @Test
      public void should_be_empty() {
         ProfileDto profile = profiles(signupDto.getId()).get(ProfileDto.class);
         assertThat(profile.getId()).isEqualTo(signupDto.getId());
      }
   }

   public static GenericType<List<ProfileDto>> ProfilesArray = new GenericType<List<ProfileDto>>() {
   };
}
