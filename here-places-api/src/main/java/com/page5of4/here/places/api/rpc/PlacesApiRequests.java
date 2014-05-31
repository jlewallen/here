package com.page5of4.here.places.api.rpc;

import com.page5of4.here.places.api.dto.PlaceDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

public interface PlacesApiRequests {
   @POST
   @Path("/places")
   @Consumes(MediaType.APPLICATION_JSON)
   UUID registerPlace(PlaceDto placeInfo);

   @GET
   @Path("/places/{id}")
   PlaceDto viewPlace(@PathParam("id") UUID id);

   @GET
   @Path("/places")
   List<PlaceDto> getPlaces();
}

