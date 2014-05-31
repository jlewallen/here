package com.page5of4.here.profiles.api.commands;

import com.page5of4.here.profiles.api.dto.ProfileDto;
import com.page5of4.here.profiles.api.rpc.ProfilesRequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.List;

public class GetProfilesCommand extends TenacityCommand<List<ProfileDto>> {
   public GetProfilesCommand() {
      super(ProfilesCommandKeys.PRFL_GET_PROFILES);
   }

   @Override
   protected List<ProfileDto> run() throws Exception {
      return ProfilesRequestFactory.get().getProfiles();
   }
}
