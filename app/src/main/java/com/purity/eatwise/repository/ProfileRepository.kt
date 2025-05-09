package com.purity.eatwise.repository

import com.purity.eatwise.data.ProfileDao
import com.purity.eatwise.model.Profile.UserProfile
import kotlinx.coroutines.flow.Flow

class ProfileRepository {


    fun ProfileDao.Companion.getAllProfiles(): Flow<List<UserProfile>> {return return TODO("Provide the return value")
    }

    class ProfileRepository(private val profileDao: ProfileDao) {
        val allProfiles: Flow<List<UserProfile>> = ProfileDao.getAllProfiles()


        suspend fun insert(profile: UserProfile) = profileDao.insertProfile(profile)

        suspend fun update(profile: UserProfile) = profileDao.updateProfile(profile)

        suspend fun delete(profile: UserProfile) = profileDao.deleteProfile(profile)

        suspend fun getById(id: Int) = profileDao.getProfileById(id)

        suspend fun saveProfile(profile: UserProfile) {
            if (profile.id == 0) insert(profile)
            else update(profile)
        }
    }
}