package com.page5of4.here.places.api.commands;

import com.page5of4.here.places.api.dto.PlaceDto;
import com.page5of4.here.places.api.rpc.PlacesRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;

public class GetPlacesCommand extends TenacityCommand<List<PlaceDto>> {
   public GetPlacesCommand() {
      super(PlacesCommandKeys.PLCS_GET_PLACES);
   }

   @Override
   protected List<PlaceDto> run() throws Exception {
      return PlacesRequestFactory.get().getPlaces();
   }
}
