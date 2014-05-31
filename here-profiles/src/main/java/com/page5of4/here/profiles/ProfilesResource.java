package com.page5of4.here.profiles;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfilesResource {
   private final ProfilesRepository repository;

   @Inject
   public ProfilesResource(ProfilesRepository repository) {
      this.repository = repository;
   }

   @GET
   public List<Profile> all() {
      return repository.getAll();
   }

   @DELETE
   public Integer deleteAll() {
      repository.deleteAll();
      return 0;
   }

   @POST
   public UUID signup(SignupInfoDto signupInfo) {
      repository.add(signupInfo.getId().toString(), signupInfo.getFirstName(), signupInfo.getLastName(), signupInfo.getEmail(), signupInfo.getPassword());
      return signupInfo.getId();
   }

   @GET
   @Path("{id}")
   public Profile profile(@PathParam("id") UUID id) {
      return repository.getById(id.toString());
   }
}
