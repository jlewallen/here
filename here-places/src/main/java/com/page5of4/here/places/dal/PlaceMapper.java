package com.page5of4.here.places.dal;

import com.page5of4.here.places.model.Address;
import com.page5of4.here.places.model.LatLon;
import com.page5of4.here.places.model.Place;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlaceMapper implements ResultSetMapper<Place> {
   public Place map(int index, ResultSet r, StatementContext ctx) throws SQLException {
      Address address = new Address(
         r.getString("street1"),
         r.getString("street2"),
         r.getString("city"),
         r.getString("state"),
         r.getString("postal_code")
      );
      LatLon location = new LatLon(
         r.getFloat("latitude"),
         r.getFloat("longitude")
      );
      return new Place(
         UUID.fromString(r.getString("id")),
         r.getString("name"),
         r.getString("description"),
         address,
         location
      );
   }
}
