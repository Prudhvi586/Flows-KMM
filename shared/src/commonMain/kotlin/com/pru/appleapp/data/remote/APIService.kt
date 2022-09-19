package com.pru.appleapp.data.remote

import com.pru.appleapp.utils.ApiState
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class APIService {
    val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            serializer = KotlinxSerializer(json)
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
            val response: T = httpClient.get {
                url(url = URLBuilder(url).build())
                headers {
                    append("Content-Type", "application/json")
                }
            }
            response
        }.onSuccess {
            emit(ApiState.Success(data = it))
        }.onFailure {
            emit(ApiState.Failure(errorMessage = it.message ?: "Error! couldn't find it."))
        }
    }
}