package com.page5of4.here.profiles;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfilesModule {
   @Bean
   public DBI dbi(Environment environment, DataSourceFactory dataSourceFactory) {
      try {
         return new DBIFactory().build(environment, dataSourceFactory, "db");
      }
      catch(ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   @Bean
   public ProfilesRepository profilesRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("DROP ALL OBJECTS");
         h.execute("CREATE TABLE profiles (id varchar(36) PRIMARY KEY, first_name VARCHAR(32), last_name VARCHAR(32), email VARCHAR(32), password VARCHAR(32))");
      }
      return dbi.onDemand(ProfilesRepository.class);
   }

   @Bean

   public ProfilesResource profilesResource(ProfilesRepository profilesRepository) {
      return new ProfilesResource(profilesRepository);
   }
}
