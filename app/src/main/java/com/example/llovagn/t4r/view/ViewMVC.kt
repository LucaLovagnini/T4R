package com.example.llovagn.t4r.view

import com.example.llovagn.t4r.State

interface ViewMVC {

    fun setMainTextView(message: String)
    fun setBackgroundWithColor(androidColor: Int)
    fun setBackgroundWithImage(image: Int)
    fun playSong(state: State)

}