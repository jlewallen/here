package com.page5of4.here.profiles.api.rpc;

import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;
import feign.ribbon.LoadBalancingTarget;

public class RequestFactory {
   private static ProfilesApiRequests api;

   public RequestFactory() {
      api = Feign.create(LoadBalancingTarget.create(ProfilesApiRequests.class, "http://here-profiles-api"), new JacksonModule(), new JAXRSModule());
   }

   public static ProfilesApiRequests get() {
      return api;
   }
}
