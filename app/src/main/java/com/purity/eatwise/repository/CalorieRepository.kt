package com.purity.eatwise.repository



import com.purity.eatwise.model.Profile.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class CalorieRepository(
    private val foodItemRepository: FooditemRepository,
    private val profileRepository: ProfileRepository
) {

    // Get all the food items added today
    fun getFoodsForToday(date: String): Flow<List<FoodItem>> {
        return foodItemRepository.getFoodsForDate(date)
    }

    // Add a food item to today's food log
    suspend fun addFoodItem(foodItem: FoodItem) {
        foodItemRepository.insert(foodItem)
    }

    // Remove a food item from today's food log
    suspend fun removeFoodItem(foodItem: FoodItem) {
        foodItemRepository.delete(foodItem)
    }

    // Get the current user's profile
    fun getUserProfile(): Flow<UserProfile> {
        return profileRepository.allProfiles.map { it.firstOrNull() ?: UserProfile() }
    }

    // Calculate total daily calories based on today's food intake
    suspend fun calculateTotalDailyCalories(date: String): Int {
        var totalCalories = 0
        foodItemRepository.getFoodsForDate(date).collect { foods ->
            totalCalories = foods.sumOf { it.calories }
        }
        return totalCalories
    }

    // Get remaining calories to hit the goal
    fun getRemainingCaloriesForToday(date: String): Flow<Int> {
        return getUserProfile().map { profile ->
            val goal = profile.dailyCalorieGoal.takeIf { it > 0 } ?: 2000
            var consumedCalories = 0
            runBlocking {
                foodItemRepository.getFoodsForDate(date).collect { foods ->
                    consumedCalories = foods.sumOf { it.calories }
                }
            }
            goal - consumedCalories
        }
    }

    // Update user's profile
    suspend fun updateUserProfile(profile: UserProfile) {
        profileRepository.saveProfile(profile)
    }
}
