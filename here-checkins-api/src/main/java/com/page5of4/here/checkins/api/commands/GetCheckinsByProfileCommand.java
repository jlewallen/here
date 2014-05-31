package com.page5of4.here.checkins.api.commands;

import com.page5of4.here.checkins.api.dto.CheckinDto;
import com.page5of4.here.checkins.api.rpc.CheckinsRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;
import java.util.UUID;

public class GetCheckinsByProfileCommand extends TenacityCommand<List<CheckinDto>> {
   private final UUID profileId;

   public GetCheckinsByProfileCommand(UUID profileId) {
      super(CheckinsCommandKeys.CHKIN_GET_CHECKINS_BY_PROFILE);
      this.profileId = profileId;
   }

   @Override
   protected List<CheckinDto> run() throws Exception {
      return CheckinsRequestFactory.get().getCheckinsByProfile(profileId);
   }
}
