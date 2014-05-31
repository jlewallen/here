package com.page5of4.here.checkins.api.messages;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class ProfileCheckedInMessage implements Serializable {
   private UUID checkinId;
   private UUID placeId;
   private UUID profileId;
   private Date time;

   public UUID getCheckinId() {
      return checkinId;
   }

   public void setCheckinId(UUID checkinId) {
      this.checkinId = checkinId;
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

   public Date getTime() {
      return time;
   }

   public void setTime(Date time) {
      this.time = time;
   }
}
