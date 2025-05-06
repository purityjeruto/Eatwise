package com.purity.eatwise.repository
import androidx.lifecycle.LiveData
import com.purity.eatwise.data.MealDao
import com.purity.eatwise.model.Meal
class MealRepository {




    class MealRepository(private val mealDao: MealDao) {

        suspend fun insertMeal(meal: Meal) {
            mealDao.insertMeal(meal)
        }

        suspend fun deleteMeal(meal: Meal) {
            mealDao.deleteMeal(meal)
        }

        fun getMealsByType(type: String): LiveData<List<Meal>> {
            return mealDao.getMealsByType(type)
        }

        fun getMealsByDate(date: String): LiveData<List<Meal>> {
            return mealDao.getMealsByDate(date)
        }
    }

}