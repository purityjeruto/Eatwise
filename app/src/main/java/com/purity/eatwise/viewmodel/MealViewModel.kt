package com.purity.eatwise.viewmodel


import androidx.lifecycle.*
import com.purity.eatwise.model.Meal
import com.purity.eatwise.repository.MealRepository
import kotlinx.coroutines.launch
class MealViewModel {
    

    

    class MealViewModel(private val repository: MealRepository) : ViewModel() {

        fun insertMeal(meal: Meal) = viewModelScope.launch {
            repository.insertMeal(meal)
        }

        fun deleteMeal(meal: Meal) = viewModelScope.launch {
            repository.deleteMeal(meal)
        }

        fun getMealsByType(type: String): LiveData<List<Meal>> {
            return repository.getMealsByType(type)
        }

        fun getMealsByDate(date: String): LiveData<List<Meal>> {
            return repository.getMealsByDate(date)
        }
    }

    class MealViewModelFactory(private val repository: MealRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MealViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}