package com.page5of4.here.webapp;

import com.codahale.metrics.JmxReporter;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.here.checkins.api.rpc.CheckinsRequestFactory;
import com.page5of4.here.common.DiagnosticsResource;
import com.page5of4.here.places.api.rpc.PlacesRequestFactory;
import com.page5of4.here.profiles.api.rpc.ProfilesRequestFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class Main extends Application<WebAppConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<WebAppConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/", "index.html"));
   }

   @SuppressWarnings("unchecked")
   @Override
   public void run(WebAppConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      ReadyFilter readyFilter = new ReadyFilter();
      readyFilter.waitOn(new ProfilesRequestFactory().start());
      readyFilter.waitOn(new PlacesRequestFactory().start());
      readyFilter.waitOn(new CheckinsRequestFactory().start());
      // environment.jersey().getResourceConfig().getContainerRequestFilters().add(readyFilter);

      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(RegistrationResource.class);
      environment.jersey().register(AvailablePlacesResource.class);
      environment.jersey().register(BusinessOwnerResource.class);
      environment.jersey().register(CheckinResource.class);
      environment.jersey().register(MyCheckinsResource.class);

      environment.jersey().register(HereExceptionManager.class);
   }

   public static class HereExceptionManager implements ExceptionMapper<RuntimeException> {

      @Override
      public Response toResponse(RuntimeException exception) {
         if(exception instanceof HystrixRuntimeException) {
            HystrixRuntimeException hystrixRuntimeException = (HystrixRuntimeException)exception;
            if(hystrixRuntimeException.getCause() instanceof WebApplicationException) {
               return ((WebApplicationException)hystrixRuntimeException.getCause()).getResponse();
            }
         }
         return null;
      }
   }
}

