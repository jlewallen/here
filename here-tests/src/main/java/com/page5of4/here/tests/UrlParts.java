package com.page5of4.here.tests;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class UrlParts {
   private final List<String> parts = Lists.newArrayList();

   public UrlParts(Object... parts) {
      for(Object part : parts) {
         this.parts.add(part.toString());
      }
   }

   public static UrlParts join(String part) {
      return new UrlParts(part);
   }

   public UrlParts join(Object... parts) {
      for(Object part : parts) {
         this.parts.add(part.toString());
      }
      return this;
   }

   public String url() {
      return "/" + StringUtils.join(parts, "/");
   }
}
