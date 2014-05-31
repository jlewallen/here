package com.page5of4.here.webapp;

import com.codahale.metrics.JmxReporter;
import com.netflix.config.ConfigurationManager;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.here.common.DiagnosticsResource;
import com.page5of4.here.places.api.rpc.PlacesRequestFactory;
import com.page5of4.here.profiles.api.rpc.ProfilesRequestFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.Properties;

public class Main extends Application<WebAppConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<WebAppConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/", "index.html"));
   }

   @Override
   public void run(WebAppConfiguration configuration, Environment environment) {
      Properties properties = new Properties();
      properties.put("here-profiles-api.ribbon.NIWSServerListClassName", "com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList");
      properties.put("here-profiles-api.ribbon.DeploymentContextBasedVipAddresses", "here-profiles.page5of4.com");
      properties.put("here-places-api.ribbon.NIWSServerListClassName", "com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList");
      properties.put("here-places-api.ribbon.DeploymentContextBasedVipAddresses", "here-places.page5of4.com");
      ConfigurationManager.loadProperties(properties);

      JmxReporter.forRegistry(environment.metrics()).build().start();

      new ProfilesRequestFactory();
      new PlacesRequestFactory();

      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(RegistrationResource.class);
      environment.jersey().register(AvailablePlacesResource.class);
      environment.jersey().register(BusinessOwnerResource.class);
   }
}

