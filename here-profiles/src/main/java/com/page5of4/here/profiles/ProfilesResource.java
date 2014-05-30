package com.page5of4.here.profiles;

import com.page5of4.here.profiles.api.dto.SignUpInfoDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfilesResource {
   private final ProfilesRepository repository;

   public ProfilesResource(ProfilesRepository repository) {
      this.repository = repository;
   }

   @POST
   public UUID signUp(SignUpInfoDto signUpInfo) {
      repository.add(signUpInfo.getId().toString(), signUpInfo.getFirstName(), signUpInfo.getLastName(), signUpInfo.getEmail(), signUpInfo.getPassword());
      return signUpInfo.getId();
   }
}
