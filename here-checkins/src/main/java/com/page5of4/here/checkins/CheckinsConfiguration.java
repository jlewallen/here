package com.page5of4.here.checkins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.dropwizard.ConfiguresEurekaClient;
import com.page5of4.dropwizard.EurekaClientConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CheckinsConfiguration extends Configuration implements ConfiguresEurekaClient {
   @Valid
   @NotNull
   @JsonProperty("eureka")
   private final EurekaClientConfiguration eureka = new EurekaClientConfiguration();

   @Valid
   @NotNull
   @JsonProperty("database")
   private DataSourceFactory database = new DataSourceFactory();

   @Override
   public EurekaClientConfiguration getEureka() {
      return eureka;
   }

   public DataSourceFactory getDatabase() {
      return database;
   }
}
