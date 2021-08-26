package com.example.myapplication.net

import com.example.myapplication.data.model.response.Cards
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface NetworkService {

  //retrofit call
  @GET("tempelate.json")
  suspend fun fetchCardsList(): ApiResponse<Cards>

}
