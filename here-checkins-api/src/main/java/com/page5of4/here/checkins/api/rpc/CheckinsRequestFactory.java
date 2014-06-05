package com.page5of4.here.checkins.api.rpc;

import com.page5of4.here.common.ClientRequestFactory;
import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;

public class CheckinsRequestFactory extends ClientRequestFactory {
   private static CheckinsApiRequests api;

   public static CheckinsApiRequests get() {
      return api;
   }

   @Override
   public String getName() {
      return "here-checkins-api";
   }

   @Override
   public String getVirtualAddress() {
      return "here-checkins.page5of4.com";
   }

   @Override
   public void configure() {
      super.configure();
      api = Feign.create(feignTarget(CheckinsApiRequests.class), new JacksonModule(), new JAXRSModule());
   }
}
