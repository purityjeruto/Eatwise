package com.purity.eatwise.ui.theme.screens.plans

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Column (modifier = Modifier.fillMaxSize()
        .padding(16.dp)) {
        //top header
        Text("Caloric friend", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))
        //calories summary
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth())
        {

            CalorieStat("200,kcal\neaten")
            CalorieStat("1800,kcal\ngoal")
            CalorieStat("80,kcal\nburned")

        }
        Spacer(modifier = Modifier.height(16.dp))


        //macronutrients bars

        NutrientBar("Protein", 40f, 120f, color.Green)
        NutrientBar("Carbs", 150f, 200f, color.Yellow)
        NutrientBar("Fats", 13.5f, 90f, color.Blue)

        Spacer(modifier = Modifier.height(16.dp))

        //meallist
        MealItem("Breakfast", "450-550kcal")
        MealItem("Snack", "180kcal")
        MealItem("Lunch", "650-750kcal")
        MealItem("Snack", "100kcal")
        MealItem("Dinner", "400kcal")

        Spacer(modifier = Modifier.height(16.dp))

        //Bottom Navigation(simplified)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth())
        {
            Text("Home")
            Text("Add")
            Text("Recipes")
            Text("Plans")
        }


    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController= rememberNavController())
}
@Composable
fun CalorieStat(value: String,label: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(label, fontSize = 14.sp, textAlign = TextAlign.Center)

    }
    @Composable
    fun  NutrientBar(name: String,current: Float,total: Float,color:Color){
        Column {
            Text("$name:${current.tolnt()}g/${total.tolnt()}g")
            LinearProgressIndicator(
                progress = current/total,
                color=color,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun MealItem(meal: String,calories: String){
        Card (
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(meal)
                Text(calories)
                Icon(Icons.Default.Add,contentDescription="Add")
            }
        }
    }





}