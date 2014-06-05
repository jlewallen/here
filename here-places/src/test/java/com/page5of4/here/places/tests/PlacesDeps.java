package com.page5of4.here.places.tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PlacesDeps {
   public static ApplicationContext get() {
      AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
      applicationContext.register(TestConfig.class);
      applicationContext.refresh();
      return applicationContext;
   }
}
