package com.whymaull.herbaid.data.database.resep

data class HerbalData(
    val herbalId: String?,
    val name: String?,
    val imageURL: String?,
    val about: String?,
    val benefits: List<String>?,
    val recipeIds: List<String>?
)
