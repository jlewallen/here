package com.page5of4.here.places;

import com.page5of4.here.places.dal.PlacesRepository;
import dagger.Module;
import dagger.Provides;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.inject.Singleton;

@Module(injects = { PlacesResource.class })
public class PlacesModule {
   private final Environment environment;
   private final DataSourceFactory dataSourceFactory;

   public PlacesModule(Environment environment, DataSourceFactory dataSourceFactory) {
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
   public PlacesRepository providePlacesRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE places (id varchar(36) PRIMARY KEY, name VARCHAR(64), description VARCHAR(1024), street1 VARCHAR(32), street2 VARCHAR(32), city VARCHAR(32), state VARCHAR(2), postalCode VARCHAR(10), latitude DECIMAL(10, 6), longitude DECIMAL(10, 6))");
      }
      return dbi.onDemand(PlacesRepository.class);
   }
}
