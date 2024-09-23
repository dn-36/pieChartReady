package database

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.project.utils.Wallets

@Entity
data class Person(
    val wallet: Wallets,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
