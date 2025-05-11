package com.purity.eatwise.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.android.gms.analytics.ecommerce.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>

    @Insert
    suspend fun insertProduct(product: com.purity.eatwise.model.Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: com.purity.eatwise.model.Product)
}