package com.page5of4.here.profiles;

import com.page5of4.codon.BusConfiguration;
import com.page5of4.codon.Subscriber;
import com.page5of4.codon.activmq.discovery.ActiveMqNetworkManager;
import com.page5of4.codon.spring.config.BusConfig;
import io.dropwizard.db.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfilesCodonConfig extends BusConfig {
   @Autowired
   private ProfilesConfiguration profilesConfiguration;

   @Bean
   @Override
   public BusConfiguration busConfiguration() {
      return profilesConfiguration.getCodonConfiguration().createBusConfiguration();
   }

   @Bean
   public ActiveMqNetworkManager activeMqNetworkManager(BusConfiguration busConfiguration, ProfilesConfiguration checkinsConfiguration, Subscriber subscriber) {
      return new ActiveMqNetworkManager(busConfiguration, subscriber, checkinsConfiguration.getZooKeeper().getCurator(), checkinsConfiguration.getCodonConfiguration().getBroker().createBroker());
   }

   @Bean
   public DataSourceFactory database() {
      return profilesConfiguration.getDatabase();
   }
}
