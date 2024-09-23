package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import database.Goal


@Dao
interface ProfileDao {

    @Insert
    suspend fun upsert(profile: Profile)


    @Delete
    suspend fun delete(profile: Profile)

    @Query("SELECT * FROM profile")
    suspend fun getAllProfile(): List<Profile>

    @Update
    suspend fun update(profile: Profile)

}