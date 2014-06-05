package com.page5of4.here.places.tests;

import com.page5of4.here.places.api.dto.AddressDto;
import com.page5of4.here.places.api.dto.LatLonDto;
import com.page5of4.here.places.api.dto.PlaceDto;

import java.util.UUID;

public class RegisterPlaceDtoBuilder {
   private PlaceDto target = new PlaceDto();

   public static RegisterPlaceDtoBuilder make() {
      return new RegisterPlaceDtoBuilder();
   }

   public RegisterPlaceDtoBuilder business() {
      target.setId(UUID.randomUUID());
      AddressDto address = new AddressDto();
      address.setStreet1("34 Blah Street");
      address.setStreet2("#456");
      address.setCity("New Haven");
      address.setState("CT");
      address.setPostalCode("06510");

      LatLonDto location = new LatLonDto();
      location.setLatitude(154);
      location.setLongitude(10);

      target.setId(UUID.randomUUID());
      target.setName("Bill's Stuff");
      target.setDescription("A friendly neighborhood business.");
      target.setAddress(address);
      target.setLocation(location);
      return this;
   }

   public PlaceDto build() {
      return target;
   }
}
