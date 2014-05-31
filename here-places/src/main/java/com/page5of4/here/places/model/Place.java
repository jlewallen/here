package com.page5of4.here.places.model;

import java.util.UUID;

public class Place {
   private UUID id;
   private String name;
   private String description;
   private Address address;
   private LatLon location;

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

   public Address getAddress() {
      return address;
   }

   public void setAddress(Address address) {
      this.address = address;
   }

   public LatLon getLocation() {
      return location;
   }

   public void setLocation(LatLon location) {
      this.location = location;
   }

   public Place() {
   }

   public Place(UUID id, String name, String description, Address address, LatLon location) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.address = address;
      this.location = location;
   }
}

