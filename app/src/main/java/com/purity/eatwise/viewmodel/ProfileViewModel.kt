package com.purity.eatwise.viewmodel




import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.purity.eatwise.model.Profile.UserProfile
import com.purity.eatwise.repository.ProfileRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    // Expose the list of profiles
    val allProfiles: StateFlow<List<UserProfile>> = repository.allProfiles
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Optionally track current selected profile
    private val _selectedProfile = MutableStateFlow(UserProfile(
        id = TODO(),
        name = TODO(),
        age = TODO(),
        weight = TODO(),
        height = TODO()
    ))
    val selectedProfile: StateFlow<UserProfile> = _selectedProfile.asStateFlow()


    fun selectProfile(profile: UserProfile) {
        _selectedProfile.value = profile
    }

    fun saveProfile(profile: UserProfile) {
        viewModelScope.launch {
            if (profile.id == 0) {
                repository.insert(profile)
            } else {
                repository.update(profile)
            }
        }
    }

    fun deleteProfile(profile: UserProfile) {
        viewModelScope.launch {
            repository.delete(profile)
        }
    }

    fun loadProfileById(id: Int) {
        viewModelScope.launch {
            val profile = repository.getById(id)
            _selectedProfile.value = profile
        }
    }
}


