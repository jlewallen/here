package com.page5of4.here.profiles.tests;

import com.page5of4.here.profiles.ProfilesModule;
import dagger.Module;

@Module(injects = { ProfilesDeps.class }, includes = ProfilesModule.class)
public class ProfilesSpecsModule {
}
