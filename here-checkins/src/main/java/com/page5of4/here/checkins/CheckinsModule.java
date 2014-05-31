package com.page5of4.here.checkins;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.inject.Singleton;

@Module(injects = { CheckinsResource.class })
public class CheckinsModule {
   private final Environment environment;
   private final DataSourceFactory dataSourceFactory;

   public CheckinsModule(Environment environment, DataSourceFactory dataSourceFactory) {
      this.environment = environment;
      this.dataSourceFactory = dataSourceFactory;
   }

   @Provides
   @Singleton
   public DBI provideDbi() {
      try {
         return new DBIFactory().build(environment, dataSourceFactory, "db");
      }
      catch(ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   @Provides
   @Singleton
   public CheckinsRepository provideCheckinsRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE checkins (id varchar(36) PRIMARY KEY, time DATETIME, profile_id varchar(36), place_id varchar(36))");
      }
      return dbi.onDemand(CheckinsRepository.class);
   }
}
