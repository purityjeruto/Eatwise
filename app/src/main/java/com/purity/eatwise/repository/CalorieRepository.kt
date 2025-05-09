package com.purity.eatwise.repository


import com.purity.eatwise.model.Profile.UserProfile
import com.purity.eatwise.viewmodel.FoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalorieRepository {




    class CalorieRepository(
        private val fooditemRepository: FooditemRepository,
        private val profileRepository: ProfileRepository
    ) {

        // Get all the food items added today
        fun getFoodsForToday(date: String): Flow<List<FoodItem>> {
            return fooditemRepository.getFoodsForDate(date)
        }

        // Add a food item to today's food log
        suspend fun addFoodItem(foodItem: FoodItem) {
            fooditemRepository.insert(foodItem)
        }

        // Remove a food item from today's food log
        suspend fun removeFoodItem(foodItem: FoodItem) {
            fooditemRepository.delete(foodItem)
        }

        // Get the current user's profile
        fun getUserProfile(): Flow<UserProfile> {
            return profileRepository.allProfiles.map { it.firstOrNull() ?: UserProfile() }
        }

        // Calculate total daily calories based on today's food intake
        suspend fun calculateTotalDailyCalories(date: String): Int {
            val foods = fooditemRepository.getFoodsForDate(date)
            return foods.sumOf { it.calories }
        }

        // Get remaining calories to hit the goal
        fun getRemainingCaloriesForToday(date: String): Flow<Int> {
            return getUserProfile().map { profile ->
                val goal = profile.dailyCalorieGoal.takeIf { it > 0 } ?: 2000
                val consumedCalories = fooditemRepository.getFoodsForDate(date).sumOf { it.calories }
                goal - consumedCalories
            }
        }

        // Update user's profile
        suspend fun updateUserProfile(profile: UserProfile) {
            profileRepository.saveProfile(profile)
        }
    }

}