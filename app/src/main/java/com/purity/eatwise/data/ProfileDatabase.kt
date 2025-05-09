package com.purity.eatwise.data

import android.content.Context
import android.provider.ContactsContract.Profile
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



class ProfileDatabase {


    @Database(entities = [Profile::class], version = 1, exportSchema = false)
    abstract class ProfileDatabase: RoomDatabase() {
        abstract fun ProfileDao(): ProfileDao

        companion object {
            @Volatile private var INSTANCE: ProfileDatabase? = null

            fun getDatabase(context: Context): ProfileDatabase {
                return INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "content_database"
                    ).build().also { INSTANCE = it }
                }
            }
        }
    }
}