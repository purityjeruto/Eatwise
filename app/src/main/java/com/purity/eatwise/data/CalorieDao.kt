package com.purity.eatwise.data



import androidx.room.*
import com.purity.eatwise.model.Profile.UserProfile
import com.purity.eatwise.viewmodel.FoodItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDao {

    // ----------- Food Items -----------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foodItem: FoodItem)

    @Delete
    suspend fun deleteFood(foodItem: FoodItem)

    @Query("SELECT * FROM food_items WHERE date = :date")
    fun getFoodsByDate(date: String): Flow<List<FoodItem>>

    @Query("DELETE FROM food_items WHERE date = :date")
    suspend fun clearFoodsByDate(date: String)

    // ----------- User Profile -----------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile LIMIT 1")
    fun getUserProfile(): Flow<UserProfile>
}
