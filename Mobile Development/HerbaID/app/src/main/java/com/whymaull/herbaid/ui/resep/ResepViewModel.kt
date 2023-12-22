package com.whymaull.herbaid.ui.resep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.whymaull.herbaid.data.api.ApiConfig
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.data.response.RecipesItem
import com.whymaull.herbaid.data.response.RecipesResponse
import com.whymaull.herbaid.pref.UserModel
import retrofit2.Call
import retrofit2.Callback

class ResepViewModel (private val reps : UserRepository) : ViewModel() {

    private val _listResep = MutableLiveData<List<RecipesItem>>()
    val listResep : LiveData<List<RecipesItem>> = _listResep

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getResep(token : String) {
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getAllRecipes("Bearer $token")

        client.enqueue(object : Callback<RecipesResponse> {
            override fun onResponse(
                call: Call<RecipesResponse>,
                response: retrofit2.Response<RecipesResponse>
            ) {
                if (response.isSuccessful) {
                    val itemList = response.body()?.recipes ?: emptyList()
                    _listResep.postValue(itemList as List<RecipesItem>?)
                    _isLoading.value = false
                } else {
                    _isLoading.value = false
                }
                _isLoading.postValue(false)
            }

            override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }

    fun getSession(): LiveData<UserModel> {
        return reps.getSession().asLiveData()
    }
}