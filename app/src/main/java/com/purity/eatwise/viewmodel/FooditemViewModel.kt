package com.purity.eatwise.viewmodel


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.purity.eatwise.data.FooditemDatabase
import com.purity.eatwise.model.Fooditem
import com.purity.eatwise.ui.theme.screens.home.FoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FooditemViewModel {

    class ProductViewModel(app: Application) : AndroidViewModel(app) {

        private val context = app.applicationContext
        private val FooditemDao = FooditemDatabase.getDatabase(app).FooditemDao()

        val allFoodItem: LiveData<List<FoodItem>> = Fooditem.getAllFooditem()

        fun addFooditem(name: String, price: Double, phone: String, imageUri: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
                val newFooditem = FoodItem(
                    name = name,
                    )

                FooditemDao.insertFoodItem(newFooditem)
            }
        }

        private fun Unit.insertFoodItem(item: FoodItem) {}

        fun updateProduct(updatedFoodItem: FoodItem) {
            viewModelScope.launch(Dispatchers.IO) {
                FooditemDao.updateFoodItem(updatedFoodItem)
            }
        }

        fun deleteProduct(foodItem: FoodItem) {
            viewModelScope.launch(Dispatchers.IO) {
                // Delete image from storage
                deleteImageFromInternalStorage(FoodItem.imagePath)
                FooditemDao.deleteFoodItem(foodItem)
            }
        }

        // Save image permanently to internal storage
        private fun saveImageToInternalStorage(uri: Uri): String {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val fileName = "IMG_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)

            inputStream?.use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }

            return file.absolutePath
        }

        private fun deleteImageFromInternalStorage(path: String) {
            try {
                val file = File(path)
                if (file.exists()) {
                    file.delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun Fooditem.Companion.getAllFooditem(): LiveData<List<FoodItem>> {}

annotation class FoodItem
