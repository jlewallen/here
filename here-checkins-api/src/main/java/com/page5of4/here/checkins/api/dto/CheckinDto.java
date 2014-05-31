package com.page5of4.here.checkins.api.dto;

import java.util.Date;
import java.util.UUID;

public class CheckinDto {
   private UUID id;
   private Date time;
   private UUID placeId;
   private UUID profileId;

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public Date getTime() {
      return time;
   }

   public void setTime(Date time) {
      this.time = time;
   }

   public UUID getPlaceId() {
      return placeId;
   }

   public void setPlaceId(UUID placeId) {
      this.placeId = placeId;
   }

   public UUID getProfileId() {
      return profileId;
   }

   public void setProfileId(UUID profileId) {
      this.profileId = profileId;
   }
}
