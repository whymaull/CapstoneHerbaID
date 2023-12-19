package com.whymaull.herbaid.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whymaull.herbaid.data.api.ApiConfig
import com.whymaull.herbaid.data.repository.UserRepository
import com.whymaull.herbaid.data.response.ResponseLogin
import com.whymaull.herbaid.pref.UserModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _loginError = MutableLiveData<String?>()
    val loginError: LiveData<String?> = _loginError

    fun signIn(email: String, password: String) {
        val client = ApiConfig.getApiService().signIn(email, password)

        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful) {
                    val appResponse = response.body()
                    if (appResponse != null) {
                        saveSession(UserModel(token = appResponse.token ?: "", isLogin = true))
                        _token.value = appResponse.token
                        _loginError.value = null
                    } else {
                        _token.value = null
                        _loginError.value = "Empty response body"
                    }
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                    _token.value = null
                    _loginError.value = errorMessage
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                _token.value = null
                _loginError.value = "Network error"
            }
        })
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
}
