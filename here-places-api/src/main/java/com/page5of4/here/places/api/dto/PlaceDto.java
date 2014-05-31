package com.page5of4.here.places.api.dto;

import java.util.UUID;

public class PlaceDto {
   private UUID id;
   private String name;
   private String description;
   private AddressDto address;
   private LatLonDto location;

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public AddressDto getAddress() {
      return address;
   }

   public void setAddress(AddressDto address) {
      this.address = address;
   }

   public LatLonDto getLocation() {
      return location;
   }

   public void setLocation(LatLonDto location) {
      this.location = location;
   }
}
