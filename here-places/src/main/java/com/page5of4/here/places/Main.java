package com.page5of4.here.places;

import com.codahale.metrics.JmxReporter;
import com.page5of4.codon.dropwizard.CodonBundle;
import com.page5of4.codon.spring.config.PublisherConfig;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperBundle;
import com.page5of4.here.common.DiagnosticsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.context.ApplicationContext;

public class Main extends Application<PlacesConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<PlacesConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new ZooKeeperBundle());
      bootstrap.addBundle(new CodonBundle(PlacesCodonConfig.class, PublisherConfig.class, PlacesModule.class));
   }

   @Override
   public void run(PlacesConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      ApplicationContext applicationContext = configuration.getCodonConfiguration().getApplicationContext();
      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(applicationContext.getBean(PlacesResource.class));
   }
}

