package com.page5of4.here.webapp;

import com.google.common.collect.Lists;
import com.netflix.client.ClientFactory;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.loadbalancer.ILoadBalancer;
import com.page5of4.here.common.ClientRequestFactory;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * This is purely a development only thing. You wouldn't want to use this in production as it would be self-defeating in
 * many situations, for example, it would prevent fallbacks from being useful. It's nice in development though because
 * you can easily tell that you're failing because things haven't synced up yet.
 *
 * I'm actually going to change this to make the exception lazy, in that if we try and chooseServer and have nothing
 * we see this... that way it doesn't stand in the way when you only want to run one middle tier service or something.
 */
@Provider
public class ReadyFilter implements ContainerRequestFilter {
   private final List<String> loadBalancerNames = Lists.newArrayList();

   @Override
   public ContainerRequest filter(ContainerRequest request) {
      DiscoveryClient discoveryClient = DiscoveryManager.getInstance().getDiscoveryClient();
      if(discoveryClient.getApplications().getRegisteredApplications().size() == 0) {
         throw new WebApplicationException(Response.status(503).entity("Waiting on Eureka").build());
      }

      List<String> waitingOn = Lists.newArrayList();
      for(String name : loadBalancerNames) {
         ILoadBalancer balancer = ClientFactory.getNamedLoadBalancer(name);
         if(balancer.getServerList(true).size() == 0) {
            waitingOn.add(name);
         }
      }
      if(!waitingOn.isEmpty()) {
         throw new WebApplicationException(Response.status(503).entity("Waiting on Load Balancers: " + StringUtils.join(waitingOn, " ")).build());
      }
      return request;
   }

   public void waitOn(ClientRequestFactory clientRequestFactory) {
      loadBalancerNames.add(clientRequestFactory.getName());
   }
}
