package com.page5of4.here.places.model;

public class LatLon {
   float latitude;
   float longitude;

   public float getLatitude() {
      return latitude;
   }

   public float getLongitude() {
      return longitude;
   }

   public LatLon() {
   }

   public LatLon(float longitude, float latitude) {
      this.longitude = longitude;
      this.latitude = latitude;
   }
}
