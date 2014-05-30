package com.page5of4.here.infrastructure;

import com.codahale.metrics.JmxReporter;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperBundle;
import com.page5of4.dropwizard.eureka.server.EurekaServerBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<InfrastructureConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<InfrastructureConfiguration> bootstrap) {
      bootstrap.addBundle(new ZooKeeperBundle());
      bootstrap.addBundle(new EurekaServerBundle());
   }

   @Override
   public void run(InfrastructureConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();
   }
}

