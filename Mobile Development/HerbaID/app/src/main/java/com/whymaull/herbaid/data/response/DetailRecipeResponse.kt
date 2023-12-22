package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName
import com.whymaull.herbaid.data.database.resep.Recipes


data class DetailRecipeResponse(

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
