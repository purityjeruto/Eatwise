package com.purity.eatwise.data

import java.util.prefs.Preferences



import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.purity.eatwise.viewmodel.CalorieViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class DataStoreHelper(private val context: Context) {
    private object PreferenceKeys {
        val KEY_NAME = stringPreferencesKey("name")
        val KEY_AGE = intPreferencesKey("age")
        val KEY_WEIGHT = floatPreferencesKey("weight")
        val KEY_HEIGHT = floatPreferencesKey("height")
        val KEY_ACTIVITY = stringPreferencesKey("activity")
        val KEY_GOAL = intPreferencesKey("goal")
    }

    val userProfileFlow: Flow<UserProfile> = context.dataStore.data
        .map { preferences ->
            UserProfile(
                name = preferences[PreferenceKeys.KEY_NAME] ?: "",
                age = preferences[PreferenceKeys.KEY_AGE] ?: 0,
                weight = preferences[PreferenceKeys.KEY_WEIGHT] ?: 0f,
                height = preferences[PreferenceKeys.KEY_HEIGHT] ?: 0f,
                activityLevel = preferences[PreferenceKeys.KEY_ACTIVITY] ?: "Normal",
                dailyCalorieGoal = preferences[PreferenceKeys.KEY_GOAL] ?: 2000
            )
        }

    suspend fun saveProfile(profile: UserProfile) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.KEY_NAME] = profile.name
            preferences[PreferenceKeys.KEY_AGE] = profile.age
            preferences[PreferenceKeys.KEY_WEIGHT] = profile.weight
            preferences[PreferenceKeys.KEY_HEIGHT] = profile.height
            preferences[PreferenceKeys.KEY_ACTIVITY] = profile.activityLevel
            preferences[PreferenceKeys.KEY_GOAL] = profile.dailyCalorieGoal
        }
    }
}
@Composable
fun ProfileScreen(viewModel: CalorieViewModel = viewModel()) {
    val userProfile by viewModel.userProfile.collectAsState()

    // Safe access with default values
    val name = userProfile.name.ifEmpty { "Not set" }
    val age = userProfile.age.takeIf { it > 0 } ?: 0

    Column {
        Text("Name: $name")
        Text("Age: $age")
        // ... other fields
    }
}