package com.page5of4.here.checkins;

import com.page5of4.codon.Bus;
import com.page5of4.here.checkins.api.messages.ProfileCheckedInMessage;

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
   private final Bus bus;

   @Inject
   public CheckinsResource(CheckinsRepository repository, Bus bus) {
      this.repository = repository;
      this.bus = bus;
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
      ProfileCheckedInMessage message = new ProfileCheckedInMessage();
      message.setCheckinId(checkin.getId());
      message.setTime(checkin.getTime());
      message.setProfileId(checkin.getProfileId());
      message.setPlaceId(checkin.getPlaceId());
      bus.publish(message);
      return repository.getById(checkin.getId().toString());
   }
}
