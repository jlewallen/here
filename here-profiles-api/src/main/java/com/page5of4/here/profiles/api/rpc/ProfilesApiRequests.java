package com.page5of4.here.profiles.api.rpc;

import com.page5of4.here.profiles.api.dto.CredentialsDto;
import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.dto.SignupInfoDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

public interface ProfilesApiRequests {
   @POST
   @Path("/profiles")
   @Consumes(MediaType.APPLICATION_JSON)
   UUID signup(SignupInfoDto signupInfo);

   @GET
   @Path("/profiles/{id}")
   ProfileDto viewProfile(@PathParam("id") UUID id);

   @POST
   @Path("/auth")
   @Consumes(MediaType.APPLICATION_JSON)
   ProfileDto authenticate(CredentialsDto credentials);
}
