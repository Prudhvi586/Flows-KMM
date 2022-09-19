package com.pru.appleapp.domain.repository

import com.pru.appleapp.constants.Constants

data class Url(private var endPoint: String) {
    val get: String
        get() = Constants.kBaseUrl.plus(endPoint)
}
