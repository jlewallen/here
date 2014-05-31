package com.page5of4.here.webapp;

import com.page5of4.here.places.api.commands.GetPlacesCommand;
import com.page5of4.here.places.api.dto.PlaceDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("available-places")
@Produces(MediaType.APPLICATION_JSON)
public class AvailablePlacesResource {
   @GET
   public List<PlaceDto> availablePlaces() {
      return new GetPlacesCommand().execute();
   }
}
