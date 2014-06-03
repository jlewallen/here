package com.page5of4.here.history;

import com.page5of4.here.history.dal.CheckinsRepository;
import com.page5of4.here.history.model.Checkin;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("checkins")
@Produces(MediaType.APPLICATION_JSON)
public class CheckinsResource {
   private final CheckinsRepository repository;

   @Inject
   public CheckinsResource(CheckinsRepository repository) {
      this.repository = repository;
   }

   @GET
   public List<Checkin> all() {
      return repository.getAll();
   }

   @GET
   @Path("{id}")
   public Checkin checkin(@PathParam("id") UUID id) {
      return repository.getById(id.toString());
   }

   @GET
   @Path("profile/{profileId}")
   public List<Checkin> checkinsForProfile(@PathParam("profileId") UUID profileId) {
      return repository.getByProfileId(profileId.toString());
   }

   @POST
   public Checkin checkin(Checkin checkin) {
      repository.add(checkin.getId().toString(), new Timestamp(new Date().getTime()), checkin.getPlaceId().toString(), checkin.getProfileId().toString());
      return repository.getById(checkin.getId().toString());
   }
}
