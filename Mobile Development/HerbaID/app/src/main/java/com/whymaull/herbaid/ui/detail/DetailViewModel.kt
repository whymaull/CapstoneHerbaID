package com.whymaull.herbaid.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.whymaull.herbaid.data.api.ApiConfig
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.data.response.DetailRecipeResponse
import com.whymaull.herbaid.pref.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (private val reps: UserRepository) : ViewModel() {

    private val _getDetailRecipe = MutableLiveData<DetailRecipeResponse?>()
    val getDetailRecipe : MutableLiveData<DetailRecipeResponse?> = _getDetailRecipe

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> = _loginError

    fun getDetailRecipe(token: String, id: String) {

        val client = ApiConfig.getApiService().getDetailResep("Bearer $token", id)
        client.enqueue(object : Callback<DetailRecipeResponse> {
            override fun onResponse(
                call: Call<DetailRecipeResponse>,
                response: Response<DetailRecipeResponse>,
            ) {
                if (response.isSuccessful) {
                    val appResponse = response.body()
                    if (appResponse != null) {
                        _getDetailRecipe.value = appResponse
                        _loginError.value = null
                    } else {
                        _getDetailRecipe.value = null
                        _loginError.value = "Tidak ada Resep"
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    _getDetailRecipe.value = null
                    _loginError.value = errorMessage
                }
            }

            override fun onFailure(call: Call<DetailRecipeResponse>, t: Throwable) {
                Log.e("DetailViewModel", "Gagal daftar: ${t.message}")
            }
        })
    }

    fun getSession(): LiveData<UserModel> {
        return reps.getSession().asLiveData()
    }
}