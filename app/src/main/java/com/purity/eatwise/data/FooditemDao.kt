package com.purity.eatwise.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.purity.eatwise.viewmodel.FoodItem
import kotlinx.coroutines.flow.Flow

class FooditemDao {



    @Dao
    interface FoodItemDao {

        @Insert
        suspend fun insertFoodItem(foodItem: FoodItem)

        @Delete
        suspend fun deleteFoodItem(foodItem: FoodItem)

        @Query("SELECT * FROM food_items WHERE date = :date")
        fun getFoodsForDate(date: String): Flow<List<FoodItem>>

        @Query("SELECT SUM(calories) FROM food_items WHERE date = :date")
        suspend fun getTotalCaloriesForDate(date: String): Int
    }

}