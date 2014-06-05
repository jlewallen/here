package com.page5of4.here.tests;

import io.dropwizard.db.DataSourceFactory;

public class InMemoryDatabase {
   public static DataSourceFactory get() {
      DataSourceFactory database = new DataSourceFactory();
      database.setDriverClass("org.h2.Driver");
      database.setUser("sa");
      database.setPassword("sa");
      database.setUrl("jdbc:h2:mem:here-profiles");
      return database;
   }
}
