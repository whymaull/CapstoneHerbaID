package com.whymaull.herbaid.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.di.Injection
import com.whymaull.herbaid.ui.detail.DetailViewModel
import com.whymaull.herbaid.ui.favorit.FavoriteViewModel
import com.whymaull.herbaid.ui.home.HomeViewModel
import com.whymaull.herbaid.ui.login.LoginViewModel
import com.whymaull.herbaid.ui.profile.ProfileViewModel
import com.whymaull.herbaid.ui.resep.ResepViewModel
import com.whymaull.herbaid.ui.splash.SplashViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (private val reps: UserRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(reps) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(reps) as T
            }
            modelClass.isAssignableFrom(ResepViewModel::class.java) -> {
                ResepViewModel(reps) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(reps) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(reps) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}