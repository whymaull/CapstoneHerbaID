package com.whymaull.herbaid.data.database.resep

import java.io.Serializable

data class Recipes(
    val complaintType: List<String>?,
    val image: String?,
    val ingredients: List<String>?,
    val instructions: List<String>?,
    val name: String?,
    val preparationTime: String?,
    val recipeId: String?
) : Serializable
