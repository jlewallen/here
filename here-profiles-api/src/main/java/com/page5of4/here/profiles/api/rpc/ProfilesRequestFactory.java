package com.page5of4.here.profiles.api.rpc;

import com.page5of4.here.common.ClientRequestFactory;
import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;

public class ProfilesRequestFactory extends ClientRequestFactory {
   private static ProfilesApiRequests api;

   public static ProfilesApiRequests get() {
      return api;
   }

   @Override
   public String getName() {
      return "here-profiles-api";
   }

   @Override
   public String getVirtualAddress() {
      return "here-profiles.page5of4.com";
   }

   @Override
   public void configure() {
      super.configure();
      api = Feign.create(feignTarget(ProfilesApiRequests.class), new JacksonModule(), new JAXRSModule());
   }
}
