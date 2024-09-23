package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.converter.converter.GoalConverters


@Database(
    entities = [Profile::class],
    version = 1
)
abstract class ProfileDataBase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

}