package com.purity.eatwise.repository



import android.content.Context
import com.purity.eatwise.data.FoodItem
import com.purity.eatwise.data.FooditemDao
import com.purity.eatwise.data.FooditemDatabase



class FooditemRepository(context: Context) {
    private val FooditemDao = FooditemDatabase.getDatabase(context).FooditemDao()

    suspend fun insertFooditem(foodItem: FoodItem) {
        FooditemDao.insertFooditem(foodItem)
    }

    fun getAllFooditem() =FooditemDao.getAllProducts()

    suspend fun deleteFooditem(foodItem: FoodItem) = FooditemDao.deleteFooditem(foodItem)
}