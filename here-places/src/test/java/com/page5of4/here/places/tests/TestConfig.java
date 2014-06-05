package com.page5of4.here.places.tests;

import com.page5of4.codon.Bus;
import com.page5of4.codon.EndpointAddress;
import com.page5of4.here.places.PlacesModule;
import com.page5of4.here.tests.InMemoryDatabase;
import io.dropwizard.db.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PlacesModule.class)
public class TestConfig {
   @Bean
   public DataSourceFactory database() {
      return InMemoryDatabase.get();
   }

   @Bean
   public Bus bus() {
      return new FakeBus();
   }

   public static class FakeBus implements Bus {
      @Override
      public <T> void publish(T t) {

      }

      @Override
      public <T> void send(T t) {

      }

      @Override
      public <T> void sendLocal(T t) {

      }

      @Override
      public <T> void send(EndpointAddress endpointAddress, T t) {

      }

      @Override
      public void subscribe(Class<?> aClass) {

      }

      @Override
      public void unsubscribe(Class<?> aClass) {

      }

      @Override
      public void listen(Class<?> aClass) {

      }

      @Override
      public void unlisten(Class<?> aClass) {

      }
   }
}
