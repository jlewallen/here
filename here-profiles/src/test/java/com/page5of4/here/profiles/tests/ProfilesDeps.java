package com.page5of4.here.profiles.tests;

import com.page5of4.here.profiles.ProfilesModule;
import com.page5of4.here.profiles.ProfilesRepository;
import com.page5of4.here.profiles.ProfilesResource;
import dagger.ObjectGraph;

import javax.inject.Inject;

import static dagger.ObjectGraph.create;

public class ProfilesDeps {
   @Inject
   ProfilesResource profilesResource;
   @Inject
   ProfilesRepository profilesRepository;

   public ProfilesResource getProfilesResource() {
      return profilesResource;
   }

   public ProfilesRepository getProfilesRepository() {
      return profilesRepository;
   }

   static ProfilesDeps singleton;

   public static ProfilesDeps get() {
      if(singleton == null) {
         ObjectGraph objectGraph = create(new ProfilesSpecsModule(), new ProfilesModule(DropwizardSpecsHelper.environment(), InMemoryDatabase.get()));
         singleton = objectGraph.get(ProfilesDeps.class);
      }
      return singleton;
   }
}
