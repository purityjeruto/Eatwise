package com.purity.eatwise.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.purity.eatwise.data.FooditemDao.FoodItemDao
import com.purity.eatwise.model.Profile.UserProfile
import com.purity.eatwise.viewmodel.FoodItem

@Database(entities = [UserProfile::class, FoodItem::class], version = 1)
abstract class CalorieDatabase : RoomDatabase() {

    // Correct the method names to match DAO interfaces
    abstract fun userProfileDao(): UserProfileDao
    abstract fun foodItemDao(): FoodItemDao

    companion object {
        @Volatile
        private var INSTANCE: CalorieDatabase? = null

        fun getDatabase(context: Context): CalorieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalorieDatabase::class.java,
                    "calorie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

annotation class UserProfileDao



