package com.page5of4.here.common;

import com.google.common.collect.Lists;
import com.netflix.config.ConfigurationManager;

import java.util.List;
import java.util.Properties;

public abstract class ClientRequestFactory {
   private static final List<ClientRequestFactory> requestFactories = Lists.newArrayList();

   public abstract String getName();

   public abstract String getVirtualAddress();

   public String getLoadBalancerTargetName() {
      return "http://" + getName();
   }

   public static List<ClientRequestFactory> getRequestFactories() {
      return requestFactories;
   }

   public void configure() {
      Properties properties = new Properties();
      properties.put(getName() + ".ribbon.NIWSServerListClassName", "com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList");
      properties.put(getName() + ".ribbon.DeploymentContextBasedVipAddresses", getVirtualAddress());
      ConfigurationManager.loadProperties(properties);
      requestFactories.add(this);
   }

   public ClientRequestFactory start() {
      configure();
      return this;
   }
}
