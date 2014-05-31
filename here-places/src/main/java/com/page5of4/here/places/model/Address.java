package com.page5of4.here.places.model;

public class Address {
   private String street1;
   private String street2;
   private String city;
   private String state;
   private String postalCode;

   public String getStreet1() {
      return street1;
   }

   public String getStreet2() {
      return street2;
   }

   public String getCity() {
      return city;
   }


   public String getState() {
      return state;
   }

   public String getPostalCode() {
      return postalCode;
   }

   public Address() {
   }

   public Address(String street1, String street2, String city, String state, String postalCode) {
      this.street1 = street1;
      this.street2 = street2;
      this.city = city;
      this.state = state;
      this.postalCode = postalCode;
   }
}
