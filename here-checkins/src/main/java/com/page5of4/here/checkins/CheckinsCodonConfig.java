package com.page5of4.here.checkins;

import com.page5of4.codon.BusConfiguration;
import com.page5of4.codon.Subscriber;
import com.page5of4.codon.activmq.discovery.ActiveMqNetworkManager;
import com.page5of4.codon.config.BusConfig;
import com.page5of4.codon.impl.TopologyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckinsCodonConfig extends BusConfig {
   @Autowired
   private CheckinsConfiguration checkinsConfiguration;

   @Bean
   @Override
   public BusConfiguration busConfiguration() {
      return checkinsConfiguration.getCodonConfiguration().createBusConfiguration(checkinsConfiguration.getBrokerConfiguration());
   }

   @Bean
   public ActiveMqNetworkManager activeMqNetworkManager(BusConfiguration busConfiguration, TopologyConfiguration topologyConfiguration, CheckinsConfiguration checkinsConfiguration, Subscriber subscriber) {
      return new ActiveMqNetworkManager(busConfiguration, topologyConfiguration, subscriber, checkinsConfiguration.getZooKeeper().getCurator(), checkinsConfiguration.getBrokerConfiguration().createBroker());
   }
}
