package com.page5of4.here.places;

import com.page5of4.codon.Bus;
import com.page5of4.here.places.api.dto.AddressDto;
import com.page5of4.here.places.api.dto.LatLonDto;
import com.page5of4.here.places.api.dto.PlaceDto;
import com.page5of4.here.places.api.messages.PlaceRegisteredMessage;
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
   private final Bus bus;

   @Inject
   public PlacesResource(PlacesRepository repository, Bus bus) {
      this.repository = repository;
      this.bus = bus;
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

      PlaceRegisteredMessage message = new PlaceRegisteredMessage();
      message.setPlaceInfo(createPlaceDto(place));
      bus.publish(message);
      return place.getId();
   }

   private PlaceDto createPlaceDto(Place place) {
      AddressDto address = new AddressDto();
      address.setStreet1(place.getAddress().getStreet1());
      address.setStreet2(place.getAddress().getStreet2());
      address.setCity(place.getAddress().getCity());
      address.setState(place.getAddress().getState());
      address.setPostalCode(place.getAddress().getPostalCode());

      LatLonDto location = new LatLonDto();
      location.setLatitude(place.getLocation().getLatitude());
      location.setLongitude(place.getLocation().getLongitude());

      PlaceDto placeDto = new PlaceDto();
      placeDto.setId(place.getId());
      placeDto.setName(place.getName());
      placeDto.setDescription(place.getDescription());
      placeDto.setAddress(address);
      placeDto.setLocation(location);
      return placeDto;
   }
}
