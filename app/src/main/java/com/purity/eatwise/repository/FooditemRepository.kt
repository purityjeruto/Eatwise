package com.purity.eatwise.repository



import com.purity.eatwise.data.FooditemDao.FoodItemDao
import com.purity.eatwise.viewmodel.FoodItem
import kotlinx.coroutines.flow.Flow

class FooditemRepository(private val foodItemDao: FoodItemDao) {

    // Get all the food items for a specific date
    fun getFoodsForDate(date: String): Flow<List<FoodItem>> {
        return foodItemDao.getFoodsByDate(date)
    }

    // Insert a new food item
    suspend fun insert(foodItem: FoodItem) {
        foodItemDao.insert(foodItem)
    }

    // Delete a food item
    suspend fun delete(foodItem: FoodItem) {
        foodItemDao.delete(foodItem)
    }
}

