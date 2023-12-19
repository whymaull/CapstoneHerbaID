package com.whymaull.herbaid.di

import android.content.Context
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.pref.UserPreferences
import com.whymaull.herbaid.pref.dataStore
import com.whymaull.herbaid.ui.profile.ProfileFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreferences.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        return UserRepository.getInstance(pref,user.token)
    }
}