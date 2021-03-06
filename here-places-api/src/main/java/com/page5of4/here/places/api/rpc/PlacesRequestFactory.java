package com.page5of4.here.places.api.rpc;

import com.page5of4.here.common.ClientRequestFactory;
import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;

public class PlacesRequestFactory extends ClientRequestFactory {
   private static PlacesApiRequests api;

   public static PlacesApiRequests get() {
      return api;
   }

   @Override
   public String getName() {
      return "here-places-api";
   }

   @Override
   public String getVirtualAddress() {
      return "here-places.page5of4.com";
   }

   @Override
   public void configure() {
      super.configure();
      api = Feign.create(feignTarget(PlacesApiRequests.class), new JacksonModule(), new JAXRSModule());
   }
}
