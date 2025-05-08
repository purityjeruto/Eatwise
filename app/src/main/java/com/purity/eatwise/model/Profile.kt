package com.purity.eatwise.model

class Profile {
    data class Profile(
        val name: String = "",
        val age: Int = 0,
        val weight: Float = 0f,
        val height: Float = 0f
    ) {
        // Calculate daily calorie goal (simplified formula)
        fun calculateDailyCalories(): Int {
            return when {
                weight > 0 && height > 0 -> (weight * 30 + height * 0.5).toInt()
                else -> 2000 // Default
            }
        }
    }
}