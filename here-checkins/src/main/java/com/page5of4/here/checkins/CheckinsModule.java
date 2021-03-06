package com.page5of4.here.checkins;

import com.page5of4.codon.Bus;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckinsModule {
   @Bean
   public DBI dbi(Environment environment, CheckinsConfiguration configuration) {
      try {
         return new DBIFactory().build(environment, configuration.getDatabase(), "db");
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
   public CheckinsResource checkinsResource(CheckinsRepository checkinsRepository, Bus bus) {
      return new CheckinsResource(checkinsRepository, bus);
   }
}
