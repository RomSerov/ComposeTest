package com.example.composetest.data.network

import com.example.composetest.data.model.Movies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/shows")
    suspend fun getAllMovies(): Response<List<Movies>>
}