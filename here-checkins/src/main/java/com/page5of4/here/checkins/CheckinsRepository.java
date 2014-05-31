package com.page5of4.here.checkins;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.sql.Timestamp;
import java.util.List;

@RegisterMapper(CheckinMapper.class)
public interface CheckinsRepository {
   @SqlUpdate("INSERT INTO checkins (id, time, place_id, profile_id) values (:id, :time, :placeId, :profileId)")
   void add(@Bind("id") String id, @Bind("time") Timestamp time, @Bind("placeId") String placeId, @Bind("profileId") String profileId);

   @SqlQuery("SELECT * FROM checkins WHERE id = :id")
   Checkin getById(@Bind("id") String id);

   @SqlQuery("SELECT * FROM checkins")
   List<Checkin> getAll();

   @SqlQuery("SELECT * FROM checkins WHERE profile_id = :profileId")
   List<Checkin> getByProfileId(@Bind("profileId") String profileId);
}
