package com.page5of4.here.history;

import com.page5of4.here.history.dal.CheckinsRepository;
import com.page5of4.here.history.dal.PlacesRepository;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoryModule {
   @Bean
   public DBI dbi(Environment environment, HistoryConfiguration historyConfiguration) {
      try {
         return new DBIFactory().build(environment, historyConfiguration.getDatabase(), "db");
      }
      catch(ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   @Bean
   public CheckinsRepository checkinsRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE checkins (id varchar(36) PRIMARY KEY, time DATETIME, profile_id varchar(36), place_id varchar(36))");
      }
      return dbi.onDemand(CheckinsRepository.class);
   }

   @Bean
   public CheckinsResource checkinsResource(CheckinsRepository checkinsRepository) {
      return new CheckinsResource(checkinsRepository);
   }

   @Bean
   public PlacesRepository placesRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE places (id varchar(36) PRIMARY KEY, name VARCHAR(64), description VARCHAR(1024), street1 VARCHAR(32), street2 VARCHAR(32), city VARCHAR(32), state VARCHAR(2), postal_code VARCHAR(10), latitude DECIMAL(10, 6), longitude DECIMAL(10, 6))");
      }
      return dbi.onDemand(PlacesRepository.class);
   }

   @Bean
   public PlacesResource placesResource(PlacesRepository placesRepository) {
      return new PlacesResource(placesRepository);
   }
}
