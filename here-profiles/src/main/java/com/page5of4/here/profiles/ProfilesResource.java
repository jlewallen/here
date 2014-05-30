package com.page5of4.here.profiles;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfilesResource {
   private final ProfilesRepository repository;

   public ProfilesResource(ProfilesRepository repository) {
      this.repository = repository;
   }

   @GET
   public List<Profile> all() {
      return repository.getAll();
   }

   @POST
   public UUID signup(SignupInfoDto signupInfo) {
      repository.add(signupInfo.getId().toString(), signupInfo.getFirstName(), signupInfo.getLastName(), signupInfo.getEmail(), signupInfo.getPassword());
      return signupInfo.getId();
   }
}
