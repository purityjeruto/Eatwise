package com.purity.eatwise.data

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.UUID

interface FooditemDao {
    @Dao
    interface FoodDao {
        @Insert
        suspend fun insert(food: FoodItem.FoodItemEntity) // Entity is Room's DB-specific version

        @Query("SELECT * FROM food_items WHERE date = :date")
        fun getFoodsByDate(date: String): Flow<List<FoodItem.FoodItemEntity>>
    }
}
// Pure business logic model (no Android dependencies)
data class FoodItem @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val calories: Int,
    val portion: String = "1 serving",
    @SuppressLint("NewApi") val date: LocalDate = LocalDate.now() // Tracking consumption date
) {
    // Helper function to convert to DB entity
    fun toEntity(): FoodItemEntity = FoodItemEntity(
        id = id,
        name = name,
        calories = calories,
        portion = portion,
        date = date.toString()
    )


@Entity(tableName = "food_items")
data class FoodItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val calories: Int,
    val portion: String,
    val date: String // Stored as ISO string (e.g., "2023-12-25")
) {
    // Convert back to domain model
    @RequiresApi(Build.VERSION_CODES.O)
    fun toFoodItem(): FoodItem = FoodItem(
        id = id,
        name = name,
        calories = calories,
        portion = portion,
        date = LocalDate.parse(date)
    )
}
}