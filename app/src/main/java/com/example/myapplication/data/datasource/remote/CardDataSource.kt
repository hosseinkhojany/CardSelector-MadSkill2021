package com.example.myapplication.data.datasource.remote

import com.example.myapplication.data.model.response.Cards
import com.example.myapplication.net.NetworkService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class CardDataSource @Inject constructor(
  private val networkService: NetworkService
) {

  suspend fun fetchCardsList(): ApiResponse<Cards> = networkService.fetchCardsList()

}
