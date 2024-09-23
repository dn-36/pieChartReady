package database.converter.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.project.utils.Goal

class GoalConverters {
    @TypeConverter
    fun fromGoal(value: Goal): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toGoal(value: String): Goal {
        return Json.decodeFromString(value)
    }
}