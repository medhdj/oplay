package com.medhdj.core.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.medhdj.core.functionnal.Response


fun <Input, Output> LiveData<Input>.map(
    mapper: (Input) -> (Output)
): MutableLiveData<Output> = Transformations.map(this, mapper) as MutableLiveData


fun <T> LiveData<T>.filter(predicate: (T) -> Boolean): LiveData<T> {
    val mutableLiveData = MediatorLiveData<T>()
    mutableLiveData.addSource(this) {
        if (predicate(it)) {
            mutableLiveData.value = it
        }
    }
    return mutableLiveData
}

fun <ERROR, DATA, Input : Response<ERROR, DATA>> LiveData<Input>.mapSuccess(): LiveData<DATA> =
    filter {
        it.isSuccess
    }.map {
        @Suppress("UNCHECKED_CAST")
        (it as Response.Success<DATA>).data
    }


fun <ERROR, DATA, Input : Response<ERROR, DATA>> LiveData<Input>.mapError(): LiveData<ERROR> =
    filter {
        it.isFailure
    }.map {
        @Suppress("UNCHECKED_CAST")
        (it as Response.Failure<ERROR>).error
    }

fun <ERROR, DATA, Input : Response<ERROR, DATA>> LiveData<Input>.mapIsLoading(): LiveData<Boolean> =
    map { it.isLoading }

fun <ERROR, DATA, Input : Response<ERROR, DATA>> LiveData<Input>.mapIsFailure(): LiveData<Boolean> =
    map { it.isFailure }

fun <ERROR, DATA, Input : Response<ERROR, DATA>> LiveData<Input>.mapIsSuccess(): LiveData<Boolean> =
    map { it.isSuccess }
