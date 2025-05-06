package com.purity.eatwise.model

import androidx.room.Entity
import androidx.room.PrimaryKey
class Meal {




    @Entity(tableName = "meals")
    data class Meal(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val calories: Int,
        val type: String, // Breakfast, Lunch, etc.
        val date: String  // Stored as "YYYY-MM-DD"
    )

}