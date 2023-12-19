package com.whymaull.herbaid.data.response


import com.google.gson.annotations.SerializedName


data class RecipesResponse(

    @field:SerializedName("recipes")
    val recipes: List<RecipesItem?>? = null
)


data class RecipesItem(

    @field:SerializedName("instructions")
    val instructions: List<String?>? = null,

    @field:SerializedName("imageURL")
    val imageURL: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("preparationTime")
    val preparationTime: String? = null,

    @field:SerializedName("ingredients")
    val ingredients: List<IngredientsItem?>? = null,

    @field:SerializedName("recipeId")
    val recipeId: String? = null,

    @field:SerializedName("favoriteCount")
    val favoriteCount: String? = null
)


data class IngredientsItem(

    @field:SerializedName("unit")
    val unit: String? = null,

    @field:SerializedName("quantity")
    val quantity: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
