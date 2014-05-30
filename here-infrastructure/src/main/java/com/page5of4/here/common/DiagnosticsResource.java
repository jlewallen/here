package com.page5of4.here.common;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("diagnostics")
public class DiagnosticsResource {
   @GET
   @Path("version")
   public String version() {
      return "version";
   }
}
