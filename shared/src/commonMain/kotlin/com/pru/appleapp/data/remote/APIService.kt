package com.pru.appleapp.data.remote

import com.pru.appleapp.utils.ApiState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class APIService {
    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    @Throws(Exception::class)
    suspend inline fun <reified T> call(
        url: String
    ): Flow<ApiState<T>> = flow {
        emit(ApiState.Loading())
        kotlin.runCatching {
            val response: HttpResponse = httpClient.get {
                url(url = URLBuilder(url).build())
                headers {
                    append("Content-Type", "application/json")
                }
            }
            response.body() as T
        }.onSuccess {
            emit(ApiState.Success(data = it))
        }.onFailure {
            emit(ApiState.Failure(errorMessage = it.message ?: "Error! couldn't find it."))
        }
    }
}