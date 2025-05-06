package com.purity.eatwise.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.purity.eatwise.model.Meal

interface MealDao {
    abstract fun insertMeal(meal: Meal)
    abstract fun deleteMeal(meal: Meal)
    abstract fun getMealsByType(type: String): LiveData<List<Meal>>
    abstract fun getMealsByDate(date: String): LiveData<List<Meal>>


    @Dao
    interface MealDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertMeal(meal: Meal)

        @Delete
        suspend fun deleteMeal(meal: Meal)

        @Query("SELECT * FROM meals WHERE type = :mealType")
        fun getMealsByType(mealType: String): LiveData<List<Meal>>

        @Query("SELECT * FROM meals WHERE date = :date")
        fun getMealsByDate(date: String): LiveData<List<Meal>>
    }

}