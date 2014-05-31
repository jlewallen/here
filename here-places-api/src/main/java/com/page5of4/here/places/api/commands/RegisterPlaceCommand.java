package com.page5of4.here.places.api.commands;

import com.page5of4.here.places.api.dto.PlaceDto;
import com.page5of4.here.places.api.rpc.PlacesRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class RegisterPlaceCommand extends TenacityCommand<PlaceDto> {
   private final PlaceDto placeInfo;

   public RegisterPlaceCommand(PlaceDto placeInfo) {
      super(PlacesCommandKeys.PLCS_REGISTER);
      this.placeInfo = placeInfo;
   }

   @Override
   protected PlaceDto run() throws Exception {
      PlacesRequestFactory.get().registerPlace(placeInfo);
      return placeInfo;
   }
}
