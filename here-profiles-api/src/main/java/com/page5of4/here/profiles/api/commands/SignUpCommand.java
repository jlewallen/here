package com.page5of4.here.profiles.api.commands;

import com.page5of4.here.profiles.api.dto.SignupInfoDto;
import com.page5of4.here.profiles.api.rpc.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

import java.util.UUID;

public class SignupCommand extends TenacityCommand<SignupInfoDto> {
   private final SignupInfoDto signupInfo;

   public SignupCommand(SignupInfoDto signupInfo) {
      super(ProfilesCommandKeys.PRFL_SIGNUP);
      this.signupInfo = signupInfo;
      this.signupInfo.setId(UUID.randomUUID());
   }

   @Override
   protected SignupInfoDto run() throws Exception {
      RequestFactory.get().signup(signupInfo);
      return signupInfo;
   }
}
