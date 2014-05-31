package com.page5of4.here.places;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.here.common.DiagnosticsResource;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<PlacesConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<PlacesConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
   }

   @Override
   public void run(PlacesConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      ObjectGraph objectGraph = ObjectGraph.create(new PlacesModule(environment, configuration.getDatabase()));
      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(objectGraph.get(PlacesResource.class));
   }
}

