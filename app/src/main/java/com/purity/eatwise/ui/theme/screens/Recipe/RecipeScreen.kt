package com.purity.eatwise.ui.theme.screens.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.purity.eatwise.R // 👈 make sure this is the correct path to your R

data class Recipe(
    val title: String,
    val description: String,
    val imageRes: Int
)

@Composable
fun RecipeScreen() {
    val recipes = listOf(
        Recipe("Avocado Toast", "A light and healthy breakfast.", R.drawable.avocado_toast),
        Recipe("Chicken Salad", "High-protein, low-carb salad.", R.drawable.chicken_salad),
        Recipe("Berry Smoothie", "Great for energy and vitamins!", R.drawable.smoothie)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Healthy Recipes", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        recipes.forEach { recipe ->
            RecipeCard(recipe = recipe, onClick = {
                // TODO: Handle click, like navigate to detail screen
            })
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(recipe.title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}




