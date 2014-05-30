package com.page5of4.here.profiles;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.here.common.DiagnosticsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<ProfilesConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<ProfilesConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
   }

   @Override
   public void run(ProfilesConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(new ProfilesModule(environment, configuration.getDatabase()).profilesResource());
   }
}

