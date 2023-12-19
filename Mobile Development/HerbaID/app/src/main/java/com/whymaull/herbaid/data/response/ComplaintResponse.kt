package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class ComplaintResponse(

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("recommendedRecipes")
    val recommendedRecipes: List<RecipesItem?>? = null
)