package com.whymaull.herbaid.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whymaull.herbaid.data.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileViewModel (private val reps : UserRepository): ViewModel() {
    fun logout() {
        viewModelScope.launch {
            reps.logout()
        }
    }
}