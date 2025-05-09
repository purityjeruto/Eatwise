package com.purity.eatwise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.purity.eatwise.model.Profile.UserProfile
import com.purity.eatwise.repository.ProfileRepository
import com.purity.eatwise.repository.FooditemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class CalorieViewModel(
    private val profileRepo: ProfileRepository,
    private val foodRepo: FooditemRepository
) : ViewModel() {

    // Profile: first available or empty fallback
    val userProfile: StateFlow<UserProfile> =
        profileRepo.allProfiles
            .map { it.firstOrNull() ?: UserProfile() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserProfile())

    private val currentDate: String = LocalDate.now().toString()

    // Daily foods for today
    val todayFoods: StateFlow<List<FoodItem>> =
        foodRepo.getFoodsForDate(currentDate)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Add a new food for today
    fun addFood(foodItem: FoodItem) {
        viewModelScope.launch {
            foodRepo.insert(foodItem.copy(date = currentDate))
        }
    }

    // Remove a food item
    fun removeFood(foodItem: FoodItem) {
        viewModelScope.launch {
            foodRepo.delete(foodItem)
        }
    }

    // Suspend function to calculate total daily calories
    suspend fun calculateDailyCalories(): Int {
        return foodRepo.getTotalCaloriesForDate(currentDate)
    }

    // Calculates remaining calories (safe fallback if profile is not loaded)
    fun getRemainingCalories(): Int {
        val goal = userProfile.value.dailyCalorieGoal.takeIf { it > 0 } ?: 2000
        val eaten = todayFoods.value.sumOf { it.calories }
        return (goal - eaten).coerceAtLeast(0)
    }

    // Update the current profile
    fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            profileRepo.saveProfile(profile)
        }
    }
}


