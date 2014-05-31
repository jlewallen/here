package com.page5of4.here.places;

import com.page5of4.here.places.dal.PlacesRepository;
import com.page5of4.here.places.model.Place;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Path("places")
@Produces(MediaType.APPLICATION_JSON)
public class PlacesResource {
   private final PlacesRepository repository;

   @Inject
   public PlacesResource(PlacesRepository repository) {
      this.repository = repository;
   }

   @GET
   public List<Place> all() {
      return repository.getAll();
   }

   @GET
   @Path("{id}")
   public Place place(@PathParam("id") UUID id) {
      return repository.getById(id.toString());
   }

   @POST
   public UUID register(Place place) {
      repository.add(place.getId().toString(),
         place.getName(),
         place.getDescription(),
         place.getAddress().getStreet1(),
         place.getAddress().getStreet2(),
         place.getAddress().getCity(),
         place.getAddress().getState(),
         place.getAddress().getPostalCode(),
         place.getLocation().getLatitude(),
         place.getLocation().getLongitude()
      );
      return place.getId();
   }
}
