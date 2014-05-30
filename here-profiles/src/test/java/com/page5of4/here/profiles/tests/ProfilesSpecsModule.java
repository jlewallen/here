package com.page5of4.here.profiles.tests;

import com.page5of4.here.profiles.ProfilesModule;
import com.page5of4.here.profiles.ProfilesRepository;
import com.page5of4.here.profiles.ProfilesResource;
import org.skife.jdbi.v2.DBI;

public class ProfilesSpecsModule extends ProfilesModule {
   public ProfilesSpecsModule() {
      super(DropwizardSpecsHelper.environment(), InMemoryDatabase.get());
   }

   DBI dbi;
   ProfilesRepository profilesRepository;
   ProfilesResource profilesResource;

   public DBI dbi() {
      if(dbi == null) dbi = super.dbi();
      return dbi;
   }

   public ProfilesRepository profilesRepository(DBI dbi) {
      if(profilesRepository == null) {
         profilesRepository = super.profilesRepository(dbi());
      }
      return profilesRepository;
   }

   public ProfilesResource profilesResource() {
      if(profilesResource == null) profilesResource = super.profilesResource();
      return profilesResource;
   }
}
