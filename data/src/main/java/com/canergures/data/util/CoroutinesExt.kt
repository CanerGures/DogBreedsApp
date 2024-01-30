package com.canergures.data.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

fun <T, V> CoroutineScope.asyncCompleteAll(
    list: List<T>,
    block: suspend (T) -> V
): List<Deferred<V>> {
    return list.map {
        async { block.invoke(it) }
    }
}