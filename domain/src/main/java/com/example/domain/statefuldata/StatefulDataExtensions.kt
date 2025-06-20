package com.example.domain.statefuldata

fun <T: List<*>> StatefulData<T>.canLoadMore(itemsPerPage: Int): Boolean {
    if (this is SuccessData) {
        return this.result.size % itemsPerPage == 0
    }
    return true
}

fun StatefulData<*>.errorName(): Any? {
    if (this is ErrorData) {
        return this.error
    }
    return null
}