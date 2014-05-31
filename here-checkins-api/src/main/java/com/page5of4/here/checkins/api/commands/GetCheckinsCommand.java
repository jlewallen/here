package com.page5of4.here.checkins.api.commands;

import com.page5of4.here.checkins.api.dto.CheckinDto;
import com.page5of4.here.checkins.api.rpc.CheckinsRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;

public class GetCheckinsCommand extends TenacityCommand<List<CheckinDto>> {
   public GetCheckinsCommand() {
      super(CheckinsCommandKeys.CHKIN_GET_CHECKINS);
   }

   @Override
   protected List<CheckinDto> run() throws Exception {
      return CheckinsRequestFactory.get().getCheckins();
   }
}
