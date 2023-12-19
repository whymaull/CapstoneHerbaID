package com.whymaull.herbaid.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.whymaull.herbaid.data.api.ApiConfig
import com.whymaull.herbaid.data.response.ResponseRegister
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isMessage = MutableLiveData<String>()
    val isMessage: LiveData<String> = _isMessage


    fun registerUser(name:String,email:String,password:String){
        _isLoading.value = true

        val client = ApiConfig.getApiService().signUp(name, email, password)

        client.enqueue(object : Callback<ResponseRegister> {
            override fun onResponse(
                call: Call<ResponseRegister>,
                response: Response<ResponseRegister>
            ) {
                if (response.isSuccessful) {
                    val appResponse = response.body()
                    if (appResponse != null) {
                        _isMessage.value = appResponse.message
                    } else {
                        _isMessage.value = "Null response received"
                    }
                } else {
                    val str = response.errorBody()!!.string()
                    try {
                        val json = JSONObject(str)

                        _isMessage.value = json.getString("message")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                _isLoading.postValue(false)
            }
            override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }

}