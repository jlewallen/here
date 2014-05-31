package com.page5of4.here.history;

import com.page5of4.codon.BusConfiguration;
import com.page5of4.codon.Subscriber;
import com.page5of4.codon.activmq.discovery.ActiveMqNetworkManager;
import com.page5of4.codon.config.BusConfig;
import com.page5of4.codon.impl.TopologyConfiguration;
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
      return historyConfiguration.getCodonConfiguration().createBusConfiguration(historyConfiguration.getBrokerConfiguration());
   }

   @Bean
   public ActiveMqNetworkManager activeMqNetworkManager(BusConfiguration busConfiguration, TopologyConfiguration topologyConfiguration, HistoryConfiguration historyConfiguration, Subscriber subscriber) {
      return new ActiveMqNetworkManager(busConfiguration, topologyConfiguration, subscriber, historyConfiguration.getZooKeeper().getCurator(), historyConfiguration.getBrokerConfiguration().createBroker());
   }

   @Bean
   public ProfileCheckedInHandler profileCheckedInHandler() {
      return new ProfileCheckedInHandler();
   }
}
