package com.page5of4.here.checkins.api.commands;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class CheckinsCommandKeyFactory implements TenacityPropertyKeyFactory {
   @Override
   public TenacityPropertyKey from(String value) {
      return CheckinsCommandKeys.valueOf(value.toUpperCase());
   }
}
