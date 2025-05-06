package com.purity.eatwise.data


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.purity.eatwise.model.Meal

class MealDatabase {

    @Database(entities = [Meal::class], version = 1, exportSchema = false)
    abstract class EatWiseDatabase : RoomDatabase() {

        abstract fun mealDao(): MealDao

        companion object {
            @Volatile
            private var INSTANCE: EatWiseDatabase? = null

            fun getDatabase(context: Context): EatWiseDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EatWiseDatabase::class.java,
                        "eatwise_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }

}