package com.example.domain.statefuldata

sealed class StatefulData<T: Any> {
    @PublishedApi
    internal inline fun <reified Data: T> innerValue(): Data? {
        return when(this) {
            is LoadingData -> null
            is SuccessData<*> -> this.result as Data
            is ErrorData<*> -> null
        }
    }

    inline fun <reified Data: T> unwrap(defaultValue: Data): Data {
        return innerValue<Data>() ?: defaultValue
    }
}

class LoadingData<T: Any> : StatefulData<T>()

class SuccessData<T: Any>(val result: T) : StatefulData<T>()

class ErrorData<T: Any>(val error: Any?) : StatefulData<T>()

