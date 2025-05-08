package com.purity.eatwise.ui.theme.screens.profile



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.changelist.Operation.AdvanceSlotsBy.name
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.purity.eatwise.viewmodel.CalorieViewModel
import com.purity.eatwise.viewmodel.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: CalorieViewModel) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }
    var weight by remember { mutableFloatStateOf(0f) }
    var height by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Tell Me About You", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Name") }
        )

        OutlinedTextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: 0 },
            label = { Text("Your Age") }
        )

        OutlinedTextField(
            value = weight.toString(),
            onValueChange = { weight = it.toFloatOrNull() ?: 0f },
            label = { Text("Weight (kg)") }
        )

        OutlinedTextField(
            value = height.toString(),
            onValueChange = { height = it.toFloatOrNull() ?: 0f },
            label = { Text("Height (cm)") }
        )

        Button(
            onClick = {
                viewModel.updateProfile(
                    UserProfile(name, age, weight, height)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

@Composable
fun ProfileScreen(
    viewModel: CalorieViewModel,
    onNext: () -> Unit  // Add this
) {
    // ... (existing code)

    Button(
        onClick = {
            viewModel.updateProfile(UserProfile(name, age, weight, height))
            onNext()  // Navigate to next screen
        }
    ) {
        Text("Save & Continue")
    }
}
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel()
) {
    val profile by viewModel.profile.collectAsState()

    Column(Modifier.padding(16.dp)) {
        Text("Your Profile", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = profile.name,
            onValueChange = { viewModel.saveProfile(profile.copy(name = it)) },
            label = { Text("Name") }
        )

        OutlinedTextField(
            value = profile.age.toString(),
            onValueChange = { viewModel.saveProfile(profile.copy(age = it.toIntOrNull() ?: 0)) },
            label = { Text("Age") }
        )

        // Add weight/height fields similarly

        Text("Daily Goal: ${profile.calculateDailyCalories()} kcal")
    }
}