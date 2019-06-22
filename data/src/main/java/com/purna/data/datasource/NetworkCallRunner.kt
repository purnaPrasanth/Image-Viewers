package com.purna.data.datasource

import com.purna.data.mappers.Mapper

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/
class NetworkCallRunner {
    suspend fun <T, E> executeForResponse(mapper: Mapper<T, E>, request: suspend () -> T): Result<E> {
        return try {
            val response = request()
            Success(mapper.map(response))
        } catch (e: Exception) {
            Error(e)
        }
    }
}