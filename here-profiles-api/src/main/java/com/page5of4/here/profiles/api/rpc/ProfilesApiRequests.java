package com.page5of4.here.profiles.api.rpc;

import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.dto.SignUpInfoDto;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

public interface ProfilesApiRequests {
   @POST
   @Path("/profiles")
   public UUID signUp(SignUpInfoDto signUpInfo);

   @GET
   @Path("/profiles/{id}")
   public ProfileDto viewProfile(@Named("id") UUID id);

   @POST
   @Path("/auth")
   public ProfileDto authenticate(@Named("email") String email, @Named("password") String password);
}
