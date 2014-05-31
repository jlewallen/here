package com.page5of4.here.checkins;

import com.codahale.metrics.JmxReporter;
import com.page5of4.codon.config.PublisherConfig;
import com.page5of4.codon.dropwizard.CodonBundle;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.dropwizard.activemq.LocalActiveMqBundle;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperBundle;
import com.page5of4.here.common.DiagnosticsResource;
import dagger.ObjectGraph;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<CheckinsConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<CheckinsConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new ZooKeeperBundle());
      bootstrap.addBundle(new LocalActiveMqBundle());
      bootstrap.addBundle(new CodonBundle(CheckinsCodonConfig.class, PublisherConfig.class));
   }

   @Override
   public void run(CheckinsConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      ObjectGraph objectGraph = ObjectGraph.create(new CheckinsModule(environment, configuration.getDatabase()));
      environment.jersey().register(DiagnosticsResource.class);
      environment.jersey().register(objectGraph.get(CheckinsResource.class));
   }
}

