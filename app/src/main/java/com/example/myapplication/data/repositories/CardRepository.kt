package com.example.myapplication.data.repositories

import androidx.annotation.WorkerThread
import com.example.myapplication.data.datasource.remote.CardDataSource
import com.example.myapplication.data.model.mapper.ErrorResponseMapper
import com.example.myapplication.data.model.response.ErrorResponse
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlin.random.Random

class CardRepository @Inject constructor(
    private val cardDataSource: CardDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : Repository {

    @WorkerThread
    fun getCards(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        // fetch cards from API
        val response = cardDataSource.fetchCardsList()

        //handles the the case when API gets an response
        response.suspendOnSuccess {
            emit(data.cards[Random.nextInt(data.cards.size)])
        }
            // handles the case when the API request gets an error response.
            // e.g., internal server error.
            .onError {
                /** maps the [ApiResponse.Failure.Error] to the [ErrorResponse] using the mapper. */
                map(ErrorResponseMapper) { onError("[Code: $statusCode]: $message") }
            }
            // handles the case when the API request gets an exception response.
            // e.g., network connection error.
            .onException { onError(message) }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)


}