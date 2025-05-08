package com.purity.eatwise.data



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


class FooditemDatabase {


    @Database(entities = [Fooditem::class], version = 1, exportSchema = false)
    abstract class ContentDatabase : RoomDatabase() {
        abstract fun contentDao(): FooditemDao

        companion object {
            @Volatile private var INSTANCE: FooditemDatabase? = null

            fun getDatabase(context: Context): FooditemDatabase {
                return INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        FooditemDatabase::class.java,
                        "content_database"
                    ).build().also { INSTANCE = it }
                }

            }
            object DefaultFoods {
                val commonFoods = listOf(
                    Fooditem.FoodItem(name = "Apple", calories = 95),
                    Fooditem.FoodItem(name = "Banana", calories = 105)
                )
            }
        }
        }
    }



