package com.whymaull.herbaid.data.response

import com.google.gson.annotations.SerializedName

data class IngredientsItem(

	@field:SerializedName("unit")
	val unit: String? = null,

	@field:SerializedName("quantity")
	val quantity: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class IdentifyHerbalResponse(

	@field:SerializedName("recipes")
	val recipes: List<RecipesItems?>? = null,

	@field:SerializedName("herbalData")
	val herbalData: HerbalData? = null
)

data class HerbalData(

	@field:SerializedName("benefits")
	val benefits: List<String?>? = null,

	@field:SerializedName("recipeIds")
	val recipeIds: List<String?>? = null,

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("about")
	val about: String? = null,

	@field:SerializedName("herbalId")
	val herbalId: String? = null
)

data class RecipesItems(

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
