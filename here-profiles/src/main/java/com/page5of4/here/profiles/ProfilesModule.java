package com.page5of4.here.profiles;

import dagger.Module;
import dagger.Provides;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import javax.inject.Singleton;

@Module(injects = { ProfilesResource.class })
public class ProfilesModule {
   private final Environment environment;
   private final DataSourceFactory dataSourceFactory;

   public ProfilesModule(Environment environment, DataSourceFactory dataSourceFactory) {
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
   public ProfilesRepository provideProfilesRepository(DBI dbi) {
      try(Handle h = dbi.open()) {
         h.execute("CREATE TABLE profiles (id varchar(36) PRIMARY KEY, first_name VARCHAR(32), last_name VARCHAR(32), email VARCHAR(32), password VARCHAR(32))");
      }
      return dbi.onDemand(ProfilesRepository.class);
   }
}
