package com.page5of4.here.history;

import com.codahale.metrics.JmxReporter;
import com.page5of4.codon.dropwizard.CodonBundle;
import com.page5of4.codon.spring.config.PublisherConfig;
import com.page5of4.dropwizard.EurekaClientBundle;
import com.page5of4.dropwizard.activemq.LocalActiveMqBundle;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperBundle;
import com.page5of4.here.common.DiagnosticsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Main extends Application<HistoryConfiguration> {
   public static void main(String[] args) throws Exception {
      new Main().run(args);
   }

   @Override
   public void initialize(Bootstrap<HistoryConfiguration> bootstrap) {
      bootstrap.addBundle(new EurekaClientBundle());
      bootstrap.addBundle(new ZooKeeperBundle());
      bootstrap.addBundle(new LocalActiveMqBundle());
      bootstrap.addBundle(new CodonBundle(HistoryCodonConfig.class, PublisherConfig.class));
   }

   @Override
   public void run(HistoryConfiguration configuration, Environment environment) {
      JmxReporter.forRegistry(environment.metrics()).build().start();

      environment.jersey().register(DiagnosticsResource.class);
   }
}

