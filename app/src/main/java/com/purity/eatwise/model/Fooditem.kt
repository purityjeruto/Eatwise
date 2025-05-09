package com.purity.eatwise.model


import androidx.room.Entity
import androidx.room.PrimaryKey


class Fooditem {

    @Entity(tableName = "food_items")
    data class Fooditem(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val calories: Int,
        val date: String // e.g., "2025-05-09"

    )


}