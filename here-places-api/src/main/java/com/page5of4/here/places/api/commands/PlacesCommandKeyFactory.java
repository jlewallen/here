package com.page5of4.here.places.api.commands;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class PlacesCommandKeyFactory implements TenacityPropertyKeyFactory {
   @Override
   public TenacityPropertyKey from(String value) {
      return PlacesCommandKeys.valueOf(value.toUpperCase());
   }
}
