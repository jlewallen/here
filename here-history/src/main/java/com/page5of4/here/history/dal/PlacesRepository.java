package com.page5of4.here.history.dal;

import com.page5of4.here.history.model.Place;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(PlaceMapper.class)
public interface PlacesRepository {
   @SqlUpdate("INSERT INTO places (id, name, description, street1, street2, city, state, postal_code, latitude, longitude) values (:id, :name, :description, :street1, :street2, :city, :state, :postalCode, :latitude, :longitude)")
   void add(@Bind("id") String id, @Bind("name") String name, @Bind("description") String description, @Bind("street1") String street1, @Bind("street2") String street2, @Bind("city") String city, @Bind("state") String state, @Bind("postalCode") String postalCode, @Bind("latitude") float latitude, @Bind("longitude") float longitude);

   @SqlQuery("SELECT * FROM places WHERE id = :id")
   Place getById(@Bind("id") String id);

   @SqlQuery("SELECT * FROM places")
   List<Place> getAll();
}
