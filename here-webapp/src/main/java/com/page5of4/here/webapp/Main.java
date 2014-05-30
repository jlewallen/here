package com.page5of4.here.webapp;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.here.common.DiagnosticsResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
      JmxReporter.forRegistry(environment.metrics()).build().start();

      environment.jersey().register(DiagnosticsResource.class);
   }
}

