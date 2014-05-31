package com.page5of4.here.checkins.api.rpc;

import com.page5of4.here.common.ClientRequestFactory;
import feign.Feign;
import feign.jackson.JacksonModule;
import feign.jaxrs.JAXRSModule;
import feign.ribbon.LoadBalancingTarget;

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
      api = Feign.create(LoadBalancingTarget.create(CheckinsApiRequests.class, getLoadBalancerTargetName()), new JacksonModule(), new JAXRSModule());
   }
}
