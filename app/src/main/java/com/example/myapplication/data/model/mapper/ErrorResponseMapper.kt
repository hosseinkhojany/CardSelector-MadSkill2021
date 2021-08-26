package com.example.myapplication.data.model.mapper

import com.example.myapplication.data.model.response.ErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [ErrorResponse] instance.
 */
object ErrorResponseMapper : ApiErrorModelMapper<ErrorResponse> {

  /**
   * maps the [ApiResponse.Failure.Error] to the [ErrorResponse] using the mapper.
   *
   * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
   * @return A customized [ErrorResponse] error response.
   */
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorResponse {
    return ErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
