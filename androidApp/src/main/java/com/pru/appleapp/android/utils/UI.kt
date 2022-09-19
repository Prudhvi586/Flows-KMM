package com.pru.appleapp.android.utils

import com.pru.appleapp.utils.ApiState

object UI {
    fun <T> ApiState<T>.handleState() {
        when(this){
            is ApiState.Failure -> TODO()
            is ApiState.Initial -> TODO()
            is ApiState.Loading -> TODO()
            is ApiState.Success -> TODO()
        }
    }
}