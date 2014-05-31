package com.page5of4.here.history;

import com.page5of4.codon.MessageHandler;
import com.page5of4.here.checkins.api.messages.ProfileCheckedInMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageHandler
public class ProfileCheckedInHandler {
   private static final Logger logger = LoggerFactory.getLogger(ProfileCheckedInHandler.class);

   @MessageHandler
   public void handle(ProfileCheckedInMessage message) {
      logger.info("Received {}", message);
   }
}
