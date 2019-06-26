package com.purna.data.mappers

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Base class for mapping on type of data to other
 *
 * @param F The type of the input data
 * @param T the type of the data to be returned
 */

interface Mapper<F, T> {
    suspend fun map(from: F): T
}

/**
 * Base Mapper for a list of items of one type to a different type
 *
 * @param F The type of the input data
 * @param T the type of the data to be returned
 */
private class MapperToListMapper<F, T>(val singleMapper: Mapper<F, T>) : Mapper<List<F>, List<T>> {
    override suspend fun map(from: List<F>): List<T> = from.map { singleMapper.map(it) }
}

/**
 * Handy Entension for converting [Mapper] to [MapperToListMapper]
 */
fun <F, T> Mapper<F, T>.toListMapper(): Mapper<List<F>, List<T>> = MapperToListMapper(this)