package com.purity.eatwise.ui.theme.screens.addingplan


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AddPlanScreen(
    onSave: (String, String, String) -> Unit = { _, _, _ -> } // Replace with real logic
) {
    var mealTitle by remember { mutableStateOf("") }
    var mealType by remember { mutableStateOf("") }
    var mealItems by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Add Meal Plan", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = mealTitle,
            onValueChange = { mealTitle = it },
            label = { Text("Meal Title") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = mealType,
            onValueChange = { mealType = it },
            label = { Text("Meal Type (e.g. Breakfast)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = mealItems,
            onValueChange = { mealItems = it },
            label = { Text("Items (comma-separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onSave(mealTitle, mealType, mealItems)
                // Clear fields or show success feedback if needed
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Save Plan", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
