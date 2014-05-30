package com.page5of4.here.profiles.api.commands;

import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.rpc.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.UUID;

public class ViewProfileCommand extends TenacityCommand<ProfileDto> {
   private final UUID id;

   public ViewProfileCommand(UUID id) {
      super(ProfilesCommandKeys.PRFL_VIEW_PROFILE);
      this.id = id;
   }

   @Override
   protected ProfileDto run() throws Exception {
      return RequestFactory.get().viewProfile(id);
   }
}
