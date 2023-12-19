package com.whymaull.herbaid.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.pref.UserModel
import kotlinx.coroutines.launch

class SplashViewModel(private val reps: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return reps.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            reps.logout()
        }
    }
}