package com.purna.data.mappers

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

interface Mapper<F, T> {
    suspend fun map(from: F): T
}

private class MapperToListMapper<F, T>(val singleMapper: Mapper<F, T>) : Mapper<List<F>, List<T>> {
    override suspend fun map(from: List<F>): List<T> = from.map { singleMapper.map(it) }
}

fun <F, T> Mapper<F, T>.toListMapper(): Mapper<List<F>, List<T>> = MapperToListMapper(this)