package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.converter.converter.GoalConverters


@Database(
    entities = [Goal::class],
    version = 1
)
@TypeConverters(GoalConverters::class)
abstract class GoalDataBase : RoomDatabase() {

        abstract fun goalDao(): GoalDao
}