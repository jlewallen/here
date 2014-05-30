package com.page5of4.here.common;

import javax.ws.rs.WebApplicationException;
import java.util.UUID;

public class Identity {
   public static final String HEADER = "Identity";

   private UUID id;

   public UUID getId() {
      return id;
   }

   public Identity(UUID id) {
      this.id = id;
   }

   public static Identity get(String token) {
      if (token == null) throw new WebApplicationException(401);
      return new Identity(UUID.fromString(token));
   }
}
