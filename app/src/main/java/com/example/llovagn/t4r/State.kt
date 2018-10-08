package com.example.llovagn.t4r

import android.graphics.Color
import java.io.Serializable
import java.util.*

private fun randomColor(): Int {
    val rnd = Random()
    return Color.argb(255, rnd.nextInt(256),
            rnd.nextInt(256), rnd.nextInt(256))
}

data class State(private val stateMessage: String,
                 private val backGroundImage: Int = 0,
                 private var backGroundColor: Int? = null) : Serializable {

    init {
        if (backGroundImage != 0) backGroundColor = Color.BLACK
    }

    fun getMessage(): String {
        return stateMessage
    }

    fun getBackgroundImage(): Int {
        return backGroundImage
    }

    fun getBackgroundColor(): Int {
        return if (backGroundColor == null) randomColor() else backGroundColor!!
    }
}