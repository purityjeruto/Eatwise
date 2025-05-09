package com.purity.eatwise.data




import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.purity.eatwise.model.Fooditem
import com.purity.eatwise.model.User

@Database(entities = [Fooditem::class, User::class], version = 3, exportSchema = false)
abstract class FooditemDatabase : RoomDatabase() {
    abstract fun fooditemDao(): Fooditem
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE:FooditemDatabase? = null

        fun getDatabase(context: Context): FooditemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FooditemDatabase::class.java,
                    "main_database"
                )
                    .fallbackToDestructiveMigration() // 💥 This clears DB on version change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

