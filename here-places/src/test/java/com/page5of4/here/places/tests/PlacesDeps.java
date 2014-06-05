package com.page5of4.here.places.tests;

import com.page5of4.here.tests.DropwizardSpecsHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PlacesDeps {
   public static ApplicationContext get() {
      AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
      applicationContext.register(TestConfig.class);
      applicationContext.getBeanFactory().registerSingleton("dropwizardEnvironment", DropwizardSpecsHelper.environment());
      // applicationContext.getBeanFactory().registerSingleton("dropwizardConfiguration", configuration);
      applicationContext.refresh();
      // ObjectGraph objectGraph = create(new ProfilesSpecsModule(), new ProfilesModule(, InMemoryDatabase.get()));
      return applicationContext;
   }
}
