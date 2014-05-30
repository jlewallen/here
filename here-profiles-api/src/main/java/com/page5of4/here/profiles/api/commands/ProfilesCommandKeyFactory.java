package com.page5of4.here.profiles.api.commands;

import com.yammer.tenacity.core.properties.TenacityPropertyKey;
import com.yammer.tenacity.core.properties.TenacityPropertyKeyFactory;

public class ProfilesCommandKeyFactory implements TenacityPropertyKeyFactory {
   @Override
   public TenacityPropertyKey from(String value) {
      return ProfilesCommandKeys.valueOf(value.toUpperCase());
   }
}
