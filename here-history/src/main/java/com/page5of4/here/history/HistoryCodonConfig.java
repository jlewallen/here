package com.page5of4.here.history;

import com.page5of4.codon.BusConfiguration;
import com.page5of4.codon.Subscriber;
import com.page5of4.codon.activmq.discovery.ActiveMqNetworkManager;
import com.page5of4.codon.spring.config.BusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryCodonConfig extends BusConfig {
   @Autowired
   private HistoryConfiguration historyConfiguration;

   @Bean
   @Override
   public BusConfiguration busConfiguration() {
      return historyConfiguration.getCodonConfiguration().createBusConfiguration();
   }

   @Bean
   public ActiveMqNetworkManager activeMqNetworkManager(BusConfiguration busConfiguration, HistoryConfiguration historyConfiguration, Subscriber subscriber) {
      return new ActiveMqNetworkManager(busConfiguration, subscriber, historyConfiguration.getZooKeeper().getCurator(), historyConfiguration.getCodonConfiguration().getBroker().createBroker());
   }

   @Bean
   public ProfileCheckedInHandler profileCheckedInHandler(CheckinsResource checkinsResource) {
      return new ProfileCheckedInHandler(checkinsResource);
   }
}
