package com.page5of4.here.places.api.messages;

import com.page5of4.here.places.api.dto.PlaceDto;

import java.io.Serializable;

public class PlaceRegisteredMessage implements Serializable {
   private PlaceDto placeInfo;

   public PlaceDto getPlaceInfo() {
      return placeInfo;
   }

   public void setPlaceInfo(PlaceDto placeInfo) {
      this.placeInfo = placeInfo;
   }
}
