package com.purity.eatwise.repository



import com.purity.eatwise.model.Content
import com.purity.eatwise.data.FooditemDao

class FooditemRepository(private val FooditemDao: FooditemDao) {
    val allContent = FooditemDao.getAllContent()

    suspend fun insert(content: Content) = FooditemDao.insertContent(content)
    suspend fun update(content: Content) = FooditemDao.updateContent(content)
    suspend fun delete(content: Content) = FooditemDao.deleteContent(content)
    suspend fun getById(id: Int) = FooditemDao.getContentById(id)
}