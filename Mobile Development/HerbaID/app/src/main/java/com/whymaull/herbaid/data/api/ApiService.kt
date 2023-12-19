package com.whymaull.herbaid.data.api

import com.whymaull.herbaid.data.response.ApiResponse
import com.whymaull.herbaid.data.response.ComplaintResponse
import com.whymaull.herbaid.data.response.FavoriteRecipesResponse
import com.whymaull.herbaid.data.response.RecipesResponse
import com.whymaull.herbaid.data.response.RecommendedRecipesResponse
import com.whymaull.herbaid.data.response.ResponseLogin
import com.whymaull.herbaid.data.response.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/api/auth/signup")
    fun signUp(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : Call<ResponseRegister>

    @FormUrlEncoded
    @POST("/api/auth/signin")
    fun signIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @POST("/api/auth/logout")
    fun logout(
        @Field("email") email: String,
        @Field("password") password: String
    ): ApiResponse


    @POST("/api/complaints")
    fun addComplaint(
        @Field("complaintType") complaintType: String
    ): Call<ComplaintResponse>

    @POST("/api/complaints/recommended")
    fun getRecommendedRecipes(
        @Field("complaintType") complaintType: String
    ): Call<RecommendedRecipesResponse>

    @GET("/api/recipes")
    fun getAllRecipes(
        @Header("Authorization") token: String,
    ): RecipesResponse

    @GET("/api/user/favorites")
    fun getFavoriteRecipes(
        @Header("Authorization") token: String,
    ): FavoriteRecipesResponse

    @POST("/api/user/favorites/add")
    fun addFavoriteRecipe(
        @Field("recipeId") recipeId: String
    ): ApiResponse

}