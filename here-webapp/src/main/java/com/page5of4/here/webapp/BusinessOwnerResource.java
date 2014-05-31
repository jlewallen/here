package com.page5of4.here.webapp;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.page5of4.here.places.api.commands.RegisterPlaceCommand;
import com.page5of4.here.places.api.dto.AddressDto;
import com.page5of4.here.places.api.dto.LatLonDto;
import com.page5of4.here.places.api.dto.PlaceDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;

@Path("businesses")
@Produces(MediaType.APPLICATION_JSON)
public class BusinessOwnerResource {
   @POST
   public PlaceDto addBusiness(PlaceDto placeInfo) {
      return new RegisterPlaceCommand(placeInfo).execute();
   }

   @POST
   @Path("fake")
   public List<PlaceDto> addFakeBusinesses() {
      List<PlaceDto> places = Lists.newArrayList();
      Random random = new Random();
      Faker faker = new Faker();
      for(long i = 0; i < 50; ++i) {
         AddressDto address = new AddressDto();
         address.setStreet1(faker.streetAddress(false));
         address.setStreet2(faker.secondaryAddress());
         address.setCity(faker.cityPrefix() + faker.citySuffix());
         address.setState(faker.stateAbbr());
         address.setPostalCode(faker.zipCode());

         LatLonDto location = new LatLonDto();
         location.setLatitude(random.nextFloat());
         location.setLongitude(random.nextFloat());

         PlaceDto place = new PlaceDto();
         place.setId(UUID.randomUUID());
         place.setName(capitalize(join(faker.words(3), " ")) + ".");
         place.setDescription(faker.paragraph(5));
         place.setAddress(address);
         place.setLocation(location);
         places.add(place);
      }
      for(PlaceDto place : places) {
         new RegisterPlaceCommand(place).execute();
      }
      return places;
   }

   public static class PlaceBuilder {
      private PlaceDto place = new PlaceDto();

      public static PlaceBuilder make() {
         return new PlaceBuilder();
      }

      public PlaceDto build() {
         return place;
      }
   }
}
