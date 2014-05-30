package com.page5of4.here.webapp;

import com.page5of4.here.common.Identity;
import com.page5of4.here.profiles.api.commands.SignupCommand;
import com.page5of4.here.profiles.api.commands.ViewProfileCommand;
import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.dto.SignupInfoDto;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("profile")
@Produces(MediaType.APPLICATION_JSON)
public class RegistrationResource {
   @POST
   public SignupInfoDto signup(SignupInfoDto signupInfo) {
      return new SignupCommand(signupInfo).execute();
   }

   @GET
   public ProfileDto profile(@HeaderParam(Identity.HEADER) String token) {
      return new ViewProfileCommand(Identity.get(token).getId()).execute();
   }
}
