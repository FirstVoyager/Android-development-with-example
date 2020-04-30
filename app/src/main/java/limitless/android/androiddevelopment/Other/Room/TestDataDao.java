package limitless.android.androiddevelopment.Other.Room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TestDataDao {

    @Query("SELECT * FROM RoomModel")
    List<RoomModel> getAll();

    @Query("SELECT * FROM RoomModel WHERE uuid = :id LIMIT 1")
    RoomModel findById(int id);

    @Query("SELECT * FROM RoomModel WHERE name = :name LIMIT 1")
    RoomModel findByName(String name);

    @Query("SELECT * FROM  RoomModel WHERE uuid IN (:ids)")
    List<RoomModel> getUsers(int[] ids);

    @Insert
    void insert(RoomModel... roomModels);

    @Delete
    void delete(RoomModel roomModel);

    @Query("DELETE FROM RoomModel WHERE name LIKE :name")
    void delete(String name);

    @Query("DELETE FROM RoomModel WHERE uuid = :id")
    void delete(int id);

}
