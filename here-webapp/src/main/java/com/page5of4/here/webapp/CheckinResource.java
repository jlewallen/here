package com.page5of4.here.webapp;

import com.page5of4.here.checkins.api.commands.CheckinCommand;
import com.page5of4.here.checkins.api.dto.CheckinDto;
import com.page5of4.here.common.Identity;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.UUID;

@Path("places/{id}/checkins")
@Produces(MediaType.APPLICATION_JSON)
public class CheckinResource {
   @POST
   public CheckinDto checkin(@HeaderParam(Identity.HEADER) String token, @PathParam("id") String placeId) {
      Identity identity = Identity.get(token);
      CheckinDto checkinInfo = new CheckinDto();
      checkinInfo.setId(UUID.randomUUID());
      checkinInfo.setPlaceId(UUID.fromString(placeId));
      checkinInfo.setProfileId(identity.getId());
      checkinInfo.setTime(new Date());
      return new CheckinCommand(checkinInfo).execute();
   }
}
