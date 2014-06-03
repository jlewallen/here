package com.page5of4.here.history;

import com.page5of4.codon.MessageHandler;
import com.page5of4.here.history.model.Address;
import com.page5of4.here.history.model.LatLon;
import com.page5of4.here.history.model.Place;
import com.page5of4.here.places.api.dto.PlaceDto;
import com.page5of4.here.places.api.messages.PlaceRegisteredMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@MessageHandler
public class PlaceRegisteredHandler {
   private static final Logger logger = LoggerFactory.getLogger(PlaceRegisteredHandler.class);
   private PlacesResource placesResource;

   @Autowired
   public PlaceRegisteredHandler(PlacesResource placesResource) {
      this.placesResource = placesResource;
   }

   @MessageHandler
   public void handle(PlaceRegisteredMessage message) {
      logger.info("Received {}", message);
      placesResource.register(createPlaceDto(message.getPlaceInfo()));
   }

   private Place createPlaceDto(PlaceDto place) {
      Address address = new Address(
         place.getAddress().getStreet1(),
         place.getAddress().getStreet2(),
         place.getAddress().getCity(),
         place.getAddress().getState(),
         place.getAddress().getPostalCode()
      );

      LatLon location = new LatLon(
         place.getLocation().getLatitude(),
         place.getLocation().getLongitude()
      );

      Place placeDto = new Place();
      placeDto.setId(place.getId());
      placeDto.setName(place.getName());
      placeDto.setDescription(place.getDescription());
      placeDto.setAddress(address);
      placeDto.setLocation(location);
      return placeDto;
   }
}
