package com.page5of4.here.places;

import com.page5of4.codon.BusConfiguration;
import com.page5of4.codon.Subscriber;
import com.page5of4.codon.activmq.discovery.ActiveMqNetworkManager;
import com.page5of4.codon.spring.config.BusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlacesCodonConfig extends BusConfig {
   @Autowired
   private PlacesConfiguration placesConfiguration;

   @Bean
   @Override
   public BusConfiguration busConfiguration() {
      return placesConfiguration.getCodonConfiguration().createBusConfiguration();
   }

   @Bean
   public ActiveMqNetworkManager activeMqNetworkManager(BusConfiguration busConfiguration, PlacesConfiguration checkinsConfiguration, Subscriber subscriber) {
      return new ActiveMqNetworkManager(busConfiguration, subscriber, checkinsConfiguration.getZooKeeper().getCurator(), checkinsConfiguration.getCodonConfiguration().getBroker().createBroker());
   }
}
