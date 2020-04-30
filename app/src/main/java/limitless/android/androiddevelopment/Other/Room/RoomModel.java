package limitless.android.androiddevelopment.Other.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomModel {

    @PrimaryKey(autoGenerate = true)
    public int uuid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "job")
    public String job;

}