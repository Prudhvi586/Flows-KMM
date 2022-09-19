package com.pru.appleapp.android.utils

import com.pru.appleapp.utils.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

object Global {
    suspend inline fun <T> Flow<ApiState<T>>.read(data: MutableStateFlow<ApiState<T>>) {
        this.collect {
            data.value = it
        }
    }

    suspend inline fun <T> Flow<ApiState<T>>.read(data: MutableSharedFlow<ApiState<T>>) {
        this.collect {
            data.emit(it)
        }
    }

    suspend inline fun <T> Flow<ApiState<T>>.read(data: kotlinx.coroutines.channels.Channel<ApiState<T>>) {
        this.collect {
            data.send(it)
        }
    }
}