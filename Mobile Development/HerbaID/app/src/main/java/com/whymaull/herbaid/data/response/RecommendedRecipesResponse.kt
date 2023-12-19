package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName
import com.whymaull.herbaid.data.database.resep.Recipes

data class RecommendedRecipesResponse(

    @field:SerializedName("recommendedRecipes")
    val recommendedRecipes: List<RecipesItem?>? = null

)
