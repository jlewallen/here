package com.page5of4.here.profiles;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ProfileMapper.class)
public interface ProfilesRepository {
   @SqlUpdate("INSERT INTO profiles (id, first_name, last_name, email, password) values (:id, :first_name, :last_name, :email, :password)")
   void add(@Bind("id") String id, @Bind("first_name") String firstName, @Bind("last_name") String lastName, @Bind("email") String email, @Bind("password") String password);

   @SqlQuery("SELECT COUNT(*) FROM profiles")
   Long numberOfProfiles();

   @SqlQuery("SELECT * FROM profiles WHERE id = :id")
   Profile getById(@Bind("id") String id);
}
