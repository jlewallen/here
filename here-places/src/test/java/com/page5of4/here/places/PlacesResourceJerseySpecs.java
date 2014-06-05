package com.page5of4.here.places;

import com.page5of4.here.places.api.dto.PlaceDto;
import com.page5of4.here.places.tests.PlacesDeps;
import com.page5of4.here.places.tests.RegisterPlaceDtoBuilder;
import com.page5of4.here.tests.UrlParts;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource.Builder;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlacesResourceJerseySpecs {
   @ClassRule
   public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(PlacesDeps.get().getBean(PlacesResource.class)).build();

   private static Builder places(Object... parts) {
      return resources.client().resource(UrlParts.join("places").join(parts).url()).type(MediaType.APPLICATION_JSON_TYPE);
   }

   @Before
   public void before() {

   }

   public static class get_places_after_startup extends PlacesResourceJerseySpecs {
      @Test
      public void should_return_a_collection() {
         List<PlaceDto> places = places().get(PlacesArray);
         assertThat(places).isNotNull();
      }
   }

   public static class add_multiple_and_get_all_places extends PlacesResourceJerseySpecs {
      @Before
      public void before() {
         super.before();

         places().post(RegisterPlaceDtoBuilder.make().business().build());
         places().post(RegisterPlaceDtoBuilder.make().business().build());
      }

      @Test
      public void should_not_be_empty() {
         assertThat(places().get(PlacesArray)).isNotEmpty();
      }
   }

   public static GenericType<List<PlaceDto>> PlacesArray = new GenericType<List<PlaceDto>>() {
   };
}
