package com.page5of4.here.webapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.page5of4.dropwizard.ConfiguresEurekaClient;
import com.page5of4.dropwizard.EurekaClientConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class WebAppConfiguration extends Configuration implements ConfiguresEurekaClient {
   @Valid
   @NotNull
   @JsonProperty("eureka")
   private final EurekaClientConfiguration eureka = new EurekaClientConfiguration();

   @Override
   public EurekaClientConfiguration getEureka() {
      return eureka;
   }
}
