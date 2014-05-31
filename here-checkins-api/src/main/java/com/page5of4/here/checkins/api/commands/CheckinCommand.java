package com.page5of4.here.checkins.api.commands;

import com.page5of4.here.checkins.api.dto.CheckinDto;
import com.page5of4.here.checkins.api.rpc.CheckinsRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class CheckinCommand extends TenacityCommand<CheckinDto> {
   private final CheckinDto placeInfo;

   public CheckinCommand(CheckinDto placeInfo) {
      super(CheckinsCommandKeys.CHKIN_REGISTER);
      this.placeInfo = placeInfo;
   }

   @Override
   protected CheckinDto run() throws Exception {
      CheckinsRequestFactory.get().checkin(placeInfo);
      return placeInfo;
   }
}
