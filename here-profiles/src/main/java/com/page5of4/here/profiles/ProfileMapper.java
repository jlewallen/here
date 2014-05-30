package com.page5of4.here.profiles;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProfileMapper implements ResultSetMapper<Profile> {
   public Profile map(int index, ResultSet r, StatementContext ctx) throws SQLException {
      return new Profile(
         UUID.fromString(r.getString("id")),
         r.getString("first_name"),
         r.getString("last_name"),
         r.getString("email"),
         r.getString("password")
      );
   }
}
