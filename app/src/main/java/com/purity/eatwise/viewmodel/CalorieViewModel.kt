package com.purity.eatwise.viewmodel





import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purity.eatwise.data.DataStoreHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CalorieViewModel(private val dataStore: DataStoreHelper) : ViewModel() {
    // User Profile State
    private val _userProfile = MutableStateFlow(UserProfile())
    val userProfile: StateFlow<UserProfile> = _userProfile.asStateFlow()

    // Today's Food List State
    private val _todayFoods = MutableStateFlow<List<FoodItem>>(emptyList())
    val todayFoods: StateFlow<List<FoodItem>> = _todayFoods.asStateFlow()

    init {
        // Load profile when ViewModel starts
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            dataStore.userProfileFlow.collect { profile ->
                _userProfile.value = profile
            }
        }
    }

    fun updateProfile(profile: UserProfile) {
        _userProfile.value = profile
        viewModelScope.launch {
            dataStore.saveProfile(profile)
        }
    }

    fun addFood(food: FoodItem) {
        _todayFoods.value = _todayFoods.value + food
    }

    fun removeFood(food: FoodItem) {
        _todayFoods.value = _todayFoods.value.filter { it != food }
    }

    fun calculateDailyCalories(): Int {
        return _todayFoods.value.sumOf { it.calories }
    }

    fun getRemainingCalories(): Int {
        return maxOf(0, _userProfile.value.dailyCalorieGoal - calculateDailyCalories())
    }

    fun clearTodayFoods() {
        _todayFoods.value = emptyList()
    }
}
