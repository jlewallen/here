package com.page5of4.here.profiles.tests;

import com.page5of4.here.profiles.ProfilesModule;
import com.page5of4.here.tests.DropwizardSpecsHelper;
import com.page5of4.here.tests.InMemoryDatabase;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ProfilesModule.class)
public class TestConfig {
   @Bean
   public DataSourceFactory database() {
      return InMemoryDatabase.get();
   }

   @Bean
   public Environment environment() {
      return DropwizardSpecsHelper.environment();
   }
}
