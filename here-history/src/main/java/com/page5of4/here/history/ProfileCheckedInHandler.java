package com.page5of4.here.history;

import com.page5of4.codon.MessageHandler;
import com.page5of4.here.checkins.api.messages.ProfileCheckedInMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@MessageHandler
public class ProfileCheckedInHandler {
   private static final Logger logger = LoggerFactory.getLogger(ProfileCheckedInHandler.class);
   private CheckinsResource checkinsResource;

   @Autowired
   public ProfileCheckedInHandler(CheckinsResource checkinsResource) {
      this.checkinsResource = checkinsResource;
   }

   @MessageHandler
   public void handle(ProfileCheckedInMessage message) {
      logger.info("Received {}", message);
      Checkin checkin = new Checkin(message.getCheckinId(), new Timestamp(message.getTime().getTime()), message.getPlaceId(), message.getProfileId());
      checkinsResource.checkin(checkin);
   }
}
