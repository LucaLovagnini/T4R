package com.awesome.llovagn.t4r.state

import android.app.Activity

interface State {

    fun execute(activity: Activity)
    fun playSong(activity: Activity)
    fun setBackgroundWithColor(activity: Activity)
    fun setBackgroundWithImage(activity: Activity)
    fun setMainTextView(activity: Activity)
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}