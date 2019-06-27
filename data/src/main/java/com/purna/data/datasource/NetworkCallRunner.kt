package com.purna.data.datasource

import com.purna.data.mappers.Mapper

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * A class for Running Network Calls in App
 *
 * Usually Singleton instance across the app
 */

class NetworkCallRunner {

    /**
     * Handy Method to execute Network Instructions
     * @param request Network Request to be run
     * @param T Network entity returned from Network
     * @param E App Entity to be created from Network Entity[T]
     * @param mapper for mapping Network Entity[T] to App Entity[E]
     * @return Result of the Network Call mapped to App Entity
     * For details on the returned type See [Success] & [Error]
     */
    suspend fun <T, E> executeForResponse(mapper: Mapper<T, E>, request: suspend () -> T): Result<E> {
        return try {
            val response = request()
            Success(mapper.map(response))
        } catch (e: Exception) {
            Error(e)
        }
    }
}