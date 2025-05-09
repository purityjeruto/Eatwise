package com.purity.eatwise.model

import androidx.room.Entity
import androidx.room.PrimaryKey



class Profile {


@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int = 0, // only one profile assumed
    val name: String,
    val age: Int,
    val weight: Float,
    val height: Float
) {
    fun calculateDailyCalories(): Int {
        return (10 * weight + 6.25 * height - 5 * age + 5).toInt()
    }

    val dailyCalorieGoal: Int
        get() = calculateDailyCalories()
}
}
