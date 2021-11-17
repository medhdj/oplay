package com.medhdj.oplay.features.common

import androidx.lifecycle.Observer

/**
 * Helper class for testing LiveData
 */
class LiveDataTestObserver<T : Any?> : Observer<T> {
    private val values: MutableList<T> = mutableListOf()

    override fun onChanged(t: T) {
        values.add(t)
    }

    fun assertHasAnyValue() {
        if (values.isEmpty()) {
            throw fail("LiveDataTestObserver never received any value")
        }
    }

    fun assertNoValue() {
        if (values.isNotEmpty()) {
            throw fail("Expected not data, but received: $values")
        }
    }

    fun assertHasValue(expected: T) {
        assertHasAnyValue()
        if (!values.any { t -> t == expected }) {
            throw fail("Expected $expected was not found")
        }
    }

    fun assertHasValue(valueAssertion: (T) -> Boolean) {
        assertHasAnyValue()
        if (!values.any { t -> valueAssertion.invoke(t) }) {
            throw fail("Assertion failed. Received $values")
        }
    }

    fun assertValue(expected: T) {
        assertHasAnyValue()
        assertValueCount(1)
        assertLastValue(expected)
    }

    fun assertValue(valueAssertion: (T) -> Boolean) {
        assertHasAnyValue()
        assertValueCount(1)
        val lastValue = values.last()
        if (!valueAssertion.invoke(lastValue)) {
            throw fail("Assertion failed. Received $lastValue")
        }
    }

    fun assertLastValue(expected: T) {
        assertHasAnyValue()
        val received: T = values.last()
        if (received != expected) {
            throw fail("Expected $expected, but received $received")
        }
    }

    fun assertLastValue(valueAssertion: (T) -> Boolean) {
        assertHasAnyValue()
        val lastValue: T = values.last()
        if (!valueAssertion.invoke(lastValue)) {
            throw fail("Assertion failed. Received $lastValue")
        }
    }

    fun assertValueCount(count: Int) {
        if (values.size != count) {
            throw fail("Expected $count values, but received ${values.size}")
        }
    }

    fun assertValues(vararg expectedValues: T) {
        assertHasAnyValue()

        if (values.size != expectedValues.size) {
            throw fail("Expected ${expectedValues.contentDeepToString()}, but received $values")
        }
        for (i in 0 until values.size) {
            if (values[i] != expectedValues[i]) {
                throw fail("Expected ${expectedValues.contentDeepToString()}, but received $values")
            }
        }
    }

    fun clearValues() {
        values.clear()
    }

    private fun fail(errorMessage: String): AssertionError = AssertionError(errorMessage)
}
