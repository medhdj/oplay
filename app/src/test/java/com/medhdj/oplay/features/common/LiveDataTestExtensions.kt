package com.medhdj.oplay.features.common

import androidx.lifecycle.LiveData

fun <T : Any?> LiveData<T>.test() = LiveDataTestObserver<T>().also {
    observeForever(it)
}
