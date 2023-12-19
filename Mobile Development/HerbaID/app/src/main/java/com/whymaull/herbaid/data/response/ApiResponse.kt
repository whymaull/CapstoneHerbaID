package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(

    @field:SerializedName("message")
    val message: String? = null,

)