package com.whymaull.herbaid.data.api

import com.whymaull.herbaid.data.response.ApiResponse
import com.whymaull.herbaid.data.response.ComplaintResponse
import com.whymaull.herbaid.data.response.DetailRecipeResponse
import com.whymaull.herbaid.data.response.FavoriteRecipesResponse
import com.whymaull.herbaid.data.response.IdentifyHerbalResponse
import com.whymaull.herbaid.data.response.RecipesResponse
import com.whymaull.herbaid.data.response.RecommendedRecipesResponse
import com.whymaull.herbaid.data.response.ResponseLogin
import com.whymaull.herbaid.data.response.ResponseRegister
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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

    @GET("/api/auth/user/{id}")
    fun getUser(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<ResponseLogin>

    @POST("/api/auth/logout")
    fun logout(
        @Field("email") email: String,
        @Field("password") password: String
    ): ApiResponse

    @Headers("Content-Type: application/json")
    @POST("/api/complaints")
    fun addComplaint(
        @Field("complaintType") complaintType: String
    ): Call<ComplaintResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/complaints/recommended")
    fun getRecommendedRecipes(
        @Field("complaintType") complaintType: String
    ): Call<RecommendedRecipesResponse>

    @GET("/api/recipe")
    fun getAllRecipes(
        @Header("Authorization") token: String
    ): Call<RecipesResponse>

    @GET("/api/recipe/{id}")
    fun getDetailResep(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<DetailRecipeResponse>

    @GET("/api/user/favorites")
    fun getFavoriteRecipes(
        @Header("Authorization") token: String
    ): FavoriteRecipesResponse

    @Headers("Content-Type: application/json")
    @POST("/api/user/favorites/add")
    fun addFavoriteRecipe(
        @Field("recipeId") recipeId: String
    ): ApiResponse

    @Multipart
    @POST("/api/herbal/identify")
    fun identifyHerbal(
        @Header("Authorization") token: String,
        @Part image : MultipartBody.Part
    ): Call<IdentifyHerbalResponse>
}