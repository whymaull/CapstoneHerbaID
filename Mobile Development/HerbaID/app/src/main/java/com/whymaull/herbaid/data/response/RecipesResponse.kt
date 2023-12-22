package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class RecipesResponse(

	@field:SerializedName("recipes")
	val recipes: List<RecipesItem?>? = null
)

data class RecipesItem(

	@field:SerializedName("instructions")
	val instructions: List<String?>? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("complaintType")
	val complaintType: String? = null,

	@field:SerializedName("preparationTime")
	val preparationTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("ingredients")
	val ingredients: List<String?>? = null,

	@field:SerializedName("recipeId")
	val recipeId: String? = null
)
