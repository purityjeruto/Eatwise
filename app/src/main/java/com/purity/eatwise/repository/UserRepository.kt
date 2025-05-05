package com.purity.eatwise.repository

import com.purity.eatwise.data.UserDao
import com.purity.eatwise.model.User


class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}