package com.page5of4.here.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.codon.dropwizard.CodonConfiguration;
import com.page5of4.codon.dropwizard.ConfiguresCodon;
import com.page5of4.dropwizard.ConfiguresEurekaClient;
import com.page5of4.dropwizard.EurekaClientConfiguration;
import com.page5of4.dropwizard.discovery.zookeeper.ConfiguresZooKeeper;
import com.page5of4.dropwizard.discovery.zookeeper.ZooKeeperConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ProfilesConfiguration extends Configuration implements ConfiguresEurekaClient, ConfiguresCodon, ConfiguresZooKeeper {
   @Valid
   @NotNull
   @JsonProperty("eureka")
   private final EurekaClientConfiguration eureka = new EurekaClientConfiguration();

   @Valid
   @NotNull
   @JsonProperty("database")
   private final DataSourceFactory database = new DataSourceFactory();

   @Valid
   @JsonProperty("codon")
   private final CodonConfiguration codonConfiguration = new CodonConfiguration();

   @Valid
   @JsonProperty("zookeeper")
   private final ZooKeeperConfiguration zookeeperConfiguration = new ZooKeeperConfiguration();

   @Override
   public EurekaClientConfiguration getEureka() {
      return eureka;
   }

   public DataSourceFactory getDatabase() {
      return database;
   }

   @Override
   public ZooKeeperConfiguration getZooKeeper() {
      return zookeeperConfiguration;
   }

   @Override
   public CodonConfiguration getCodonConfiguration() {
      return codonConfiguration;
   }
}
