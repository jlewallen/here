package com.page5of4.here.profiles;

import com.page5of4.here.profiles.tests.ProfilesDeps;
import com.page5of4.here.profiles.tests.ProfilesSpecsModule;
import com.sun.jersey.api.client.GenericType;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfilesResourceJerseySpecs {
   @ClassRule
   public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(ProfilesDeps.get().getProfilesResource()).build();

   @Test
   public void test_get_profiles_when_empty() {
      List<Profile> profiles = resources.client().resource("/profiles").get(new GenericType<List<Profile>>() {
      });
      assertThat(profiles).isEmpty();
   }
}
