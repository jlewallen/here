package com.page5of4.here.webapp;

import com.page5of4.here.checkins.api.commands.GetCheckinsByProfileCommand;
import com.page5of4.here.checkins.api.dto.CheckinDto;
import com.page5of4.here.common.Identity;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("profile/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class MyCheckinsResource {
   @GET
   public List<CheckinDto> myCheckins(@HeaderParam(Identity.HEADER) String token) {
      Identity identity = Identity.get(token);
      return new GetCheckinsByProfileCommand(identity.getId()).execute();
   }
}
