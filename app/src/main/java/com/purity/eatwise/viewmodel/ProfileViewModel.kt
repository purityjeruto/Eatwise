package com.purity.eatwise.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purity.eatwise.model.Profile
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class ProfileViewModel {


    class ProfileViewModel(
        private val profile1: Profile1
    ) : ViewModel() {

        val profile: StateFlow<Profile> = profile1.profileFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Profile()
        )

        fun saveProfile(profile: Profile) {
            viewModelScope.launch {
                profile1.saveProfile(profile)
            }
        }
    }
}