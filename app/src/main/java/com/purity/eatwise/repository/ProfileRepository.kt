package com.purity.eatwise.repository

import com.purity.eatwise.data.ProfileDao
 // Replace with your actual profile class
import com.purity.eatwise.model.Profile.UserProfile

class ProfileRepository(private val profileDao: ProfileDao) {

        val allProfiles = profileDao.getAllProfiles()

        suspend fun insert(profile: UserProfile) = profileDao.insertProfile(profile)

        suspend fun update(profile: UserProfile) = profileDao.updateProfile(profile)

        suspend fun delete(profile: UserProfile) = profileDao.deleteProfile(profile)

        suspend fun getById(id: Int) = profileDao.getProfileById(id)

        suspend fun saveProfile(profile: UserProfile) {
                if (profile.id == 0) insert(profile)
                else update(profile)
        }

}

