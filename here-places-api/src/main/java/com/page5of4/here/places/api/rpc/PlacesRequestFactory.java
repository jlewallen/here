package com.page5of4.here.places.api.rpc;

import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;
import feign.ribbon.LoadBalancingTarget;

public class PlacesRequestFactory {
   private static PlacesApiRequests api;

   public PlacesRequestFactory() {
      api = Feign.create(LoadBalancingTarget.create(PlacesApiRequests.class, "http://here-places-api"), new JacksonModule(), new JAXRSModule());
   }

   public static PlacesApiRequests get() {
      return api;
   }
}
