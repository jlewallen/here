package com.page5of4.here.places.tests;

import com.page5of4.codon.Bus;
import com.page5of4.codon.EndpointAddress;

public class FakeBus implements Bus {
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
