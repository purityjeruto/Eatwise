package com.purity.eatwise.repository



import android.content.Context
import com.purity.eatwise.data.FooditemDatabase
import com.purity.eatwise.viewmodel.FoodItem


class FooditemRepository(context: Context) {
    private val FooditemDao = FooditemDatabase.getDatabase(context).FooditemDao()

    suspend fun insertFooditem(foodItem: FoodItem) {
        FooditemDao.insertFooditem(foodItem)
    }

    fun getAllFooditem() =FooditemDao.getAllFooditem()

    suspend fun deleteFooditem(foodItem: FoodItem) = FooditemDao.deleteFooditem(foodItem)
}

private fun Unit.deleteFooditem(foodItem: FoodItem): Any {
    TODO("Not yet implemented")
}

private fun Unit.getAllFooditem(): Any {
    TODO("Not yet implemented")
}

private fun Unit.insertFooditem(foodItem: FoodItem) {
    TODO("Not yet implemented")
}
