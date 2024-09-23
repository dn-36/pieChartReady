package database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PeopleDao {

    @Insert
    suspend fun upsert(person: Person)


    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM person")
    suspend fun getAllPeople(): List<Person>

    @Update
    suspend fun update(person: Person)

}