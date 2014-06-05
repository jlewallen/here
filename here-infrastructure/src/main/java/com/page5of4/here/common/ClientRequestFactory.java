package com.page5of4.here.common;

import com.google.common.collect.Lists;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import feign.Request;
import feign.RequestTemplate;
import feign.ribbon.LoadBalancingTarget;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
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
      properties.put(getName() + ".ribbon.NIWSServerListClassName", DiscoveryEnabledNIWSServerList.class.getName());
      properties.put(getName() + ".ribbon.DeploymentContextBasedVipAddresses", getVirtualAddress());
      ConfigurationManager.loadProperties(properties);
      requestFactories.add(this);
   }

   public ClientRequestFactory start() {
      configure();
      return this;
   }

   public <T> LoadBalancingTarget<T> feignTarget(Class<T> klass) {
      URI asUri = URI.create(getLoadBalancerTargetName());
      return new LoadBalancingTarget<T>(klass, asUri.getScheme(), asUri.getHost()) {
         @Override
         public Request apply(RequestTemplate requestTemplate) {
            if(lb().getServerList(true).isEmpty()) {
               throw new WebApplicationException(Response.status(503).entity("Waiting on dependency: " + getName()).build());
            }
            return super.apply(requestTemplate);
         }
      };
   }
}
