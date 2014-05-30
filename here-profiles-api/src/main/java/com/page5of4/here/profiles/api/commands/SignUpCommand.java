package com.page5of4.here.profiles.api.commands;

import com.page5of4.here.profiles.api.dto.SignUpInfoDto;
import com.page5of4.here.profiles.api.rpc.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.UUID;

public class SignUpCommand extends TenacityCommand<UUID> {
   private final SignUpInfoDto signUpInfo;

   protected SignUpCommand(SignUpInfoDto signUpInfo) {
      super(ProfilesCommandKeys.PRFL_SIGNUP);
      this.signUpInfo = signUpInfo;
   }

   @Override
   protected UUID run() throws Exception {
      return RequestFactory.get().signUp(signUpInfo);
   }
}
