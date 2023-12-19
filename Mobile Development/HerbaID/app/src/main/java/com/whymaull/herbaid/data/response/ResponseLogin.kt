package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

    @field:SerializedName("token")
    val token: String? = null
)
