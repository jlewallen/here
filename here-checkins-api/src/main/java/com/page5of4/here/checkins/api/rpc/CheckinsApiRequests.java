package com.page5of4.here.checkins.api.rpc;

import com.page5of4.here.checkins.api.dto.CheckinDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

public interface CheckinsApiRequests {
   @POST
   @Path("/checkins")
   @Consumes(MediaType.APPLICATION_JSON)
   CheckinDto checkin(CheckinDto placeInfo);

   @GET
   @Path("/checkins/{id}")
   CheckinDto viewCheckin(@PathParam("id") UUID id);

   @GET
   @Path("/checkins")
   List<CheckinDto> getCheckins();
}

