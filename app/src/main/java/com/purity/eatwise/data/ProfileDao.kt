package com.purity.eatwise.data


import androidx.room.*
import com.purity.eatwise.model.Profile.UserProfile
import kotlinx.coroutines.flow.Flow


@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: UserProfile)

    @Update
    suspend fun updateProfile(profile: UserProfile)

    @Delete
    suspend fun deleteProfile(profile: UserProfile)

    @Query("SELECT * FROM user_profile")
    fun getAllProfiles(): Flow<List<UserProfile>>

    @Query("SELECT * FROM user_profile WHERE id = :id")
    suspend fun getProfileById(id: Int): UserProfile

    companion object
}
