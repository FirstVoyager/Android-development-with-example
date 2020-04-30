package limitless.android.androiddevelopment.Other.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomModel.class}, version = 1)
public abstract class TestDatabase extends RoomDatabase {

    public abstract TestDataDao testDataDao();

}
