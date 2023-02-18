package com.example.appbystep

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KinoAPI {
    @Headers(
        "X-API-KEY:${BasicSettings.TOKEN}",
        "Content-type:application/json"
    )
    @GET("top")
    suspend fun getFilms(@Query("type") topName:String, @Query("page") count:Int) :Response<FilmListData>
    @GET("{id}")
    suspend fun getFilmData()
}