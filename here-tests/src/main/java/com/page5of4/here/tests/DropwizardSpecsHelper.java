package com.page5of4.here.tests;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.setup.Environment;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public class DropwizardSpecsHelper {
   public static Environment environment() {
      MetricRegistry metricRegistry = new MetricRegistry();
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      return new Environment("specs", Jackson.newObjectMapper(), validatorFactory.getValidator(), metricRegistry, Thread.currentThread().getContextClassLoader());
   }
}
