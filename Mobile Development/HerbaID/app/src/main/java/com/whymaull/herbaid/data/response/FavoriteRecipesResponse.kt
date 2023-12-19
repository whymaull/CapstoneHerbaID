package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class FavoriteRecipesResponse(

    @field:SerializedName("favoriteRecipes")
    val favoriteRecipes: List<RecipesItem?>? = null

)