package com.purity.eatwise.data


import androidx.lifecycle.LiveData
import androidx.room.*




class FooditemDao {

    @Dao
    interface FooditemDao {
        @Query("SELECT * FROM food_items")
        fun <FoodItem> getAllFooditems(): LiveData<List<FoodItem>>

        @Insert
        suspend fun <FoodItem> insertFooditem(foodItem: FoodItem)

        @Update
        suspend fun <FoodItem> updateFooditem(foodItem: FoodItem)

        @Delete
        suspend fun <FoodItem> deleteFooditem(foodItem: FoodItem)
    }
}