package com.example.llovagn.t4r.view

import android.media.Image
import com.example.llovagn.t4r.State

interface ViewMVC {

    fun setMainTextView(message: String)
    fun setBackgroundWithColor(androidColor: Int)
    fun setBackgroundWithImage(color: Image)
    fun playSong(state: State)

}