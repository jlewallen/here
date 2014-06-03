package com.page5of4.here.history.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Checkin {
   private UUID id;
   private Timestamp time;
   private UUID placeId;
   private UUID profileId;

   public UUID getId() {
      return id;
   }

   public Timestamp getTime() {
      return time;
   }

   public UUID getPlaceId() {
      return placeId;
   }

   public UUID getProfileId() {
      return profileId;
   }

   public Checkin() {
   }

   public Checkin(UUID id, Timestamp time, UUID placeId, UUID profileId) {
      this.id = id;
      this.time = time;
      this.placeId = placeId;
      this.profileId = profileId;
   }
}
