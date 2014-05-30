package com.page5of4.here.profiles.api.commands;

import com.page5of4.here.profiles.api.rpc.RequestFactory;
import com.yammer.tenacity.core.TenacityCommand;

public class DeleteAllProfilesCommand  extends TenacityCommand<Integer> {
   public DeleteAllProfilesCommand  () {
      super(ProfilesCommandKeys.PRFL_DELETE_ALL);
   }

   @Override
   protected Integer run() throws Exception {
      return RequestFactory.get().deleteAll();
   }
}
