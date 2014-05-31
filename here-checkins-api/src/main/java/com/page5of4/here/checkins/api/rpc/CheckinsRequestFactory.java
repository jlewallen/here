package com.page5of4.here.checkins.api.rpc;

import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;
import feign.ribbon.LoadBalancingTarget;

public class CheckinsRequestFactory {
   private static CheckinsApiRequests api;

   public CheckinsRequestFactory() {
      api = Feign.create(LoadBalancingTarget.create(CheckinsApiRequests.class, "http://here-checkins-api"), new JacksonModule(), new JAXRSModule());
   }

   public static CheckinsApiRequests get() {
      return api;
   }
}
