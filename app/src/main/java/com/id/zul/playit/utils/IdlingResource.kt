package com.id.zul.playit.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    val myIdle = CountingIdlingResource("GLOBAL")

    fun increment() {
        myIdle.increment()
    }

    fun decrement() {
        myIdle.decrement()
    }
}