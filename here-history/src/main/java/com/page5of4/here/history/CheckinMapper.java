package com.page5of4.here.history;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CheckinMapper implements ResultSetMapper<Checkin> {
   public Checkin map(int index, ResultSet r, StatementContext ctx) throws SQLException {
      return new Checkin(
         UUID.fromString(r.getString("id")),
         r.getTimestamp("time"),
         UUID.fromString(r.getString("place_id")),
         UUID.fromString(r.getString("profile_id"))
      );
   }
}
