package com.example.llovagn.t4r

data class State(private val stateMessage: String) {
    fun getMessage(): String {
        return stateMessage
    }
}