package com.example.llovagn.t4r

import java.io.Serializable

data class State(private val stateMessage: String) : Serializable{
    fun getMessage(): String {
        return stateMessage
    }
}