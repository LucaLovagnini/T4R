package com.example.llovagn.t4r.state

import android.app.Activity

interface State {

    fun execute(activity: Activity)
    fun getMessage(): String
    fun playSong(activity: Activity)
    fun setBackgroundWithColor(activity: Activity)
    fun setBackgroundWithImage(activity: Activity)
    fun setMainTextView(activity: Activity)

}