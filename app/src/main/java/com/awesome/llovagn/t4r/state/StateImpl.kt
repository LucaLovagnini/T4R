package com.awesome.llovagn.t4r.state

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageSwitcher
import android.widget.TextSwitcher
import com.awesome.llovagn.t4r.MainActivity
import com.awesome.llovagn.t4r.R
import java.util.*

private fun randomColor(): Int {
    val rnd = Random()
    val random = Color.argb(255, rnd.nextInt(256),
            rnd.nextInt(256), rnd.nextInt(256))
    return random
}

class StateImpl(private val stateMessage: String,
                private val backGroundImage: Int = 0,
                private val song: Int = 0,
                private var backGroundColor: Int? = null) : State {

    init {
        if (backGroundImage != 0)
            backGroundColor = Color.BLACK
    }

    override fun execute(activity: Activity) {
        setBackgroundWithColor(activity)
        setBackgroundWithImage(activity)
        setMainTextView(activity)
        playSong(activity)
    }

    override fun playSong(activity: Activity) {
        val mainActivity: MainActivity = activity as MainActivity
        mainActivity.playSong(song)
    }

    override fun setBackgroundWithColor(activity: Activity) {
        val backgroundView = activity.findViewById<View>(R.id.view_color_background)
        if (backGroundColor == null || backGroundColor == 0) backGroundColor = randomColor()
        val colorBackgroundDrawable = backgroundView.background as ColorDrawable
        val colorFrom = colorBackgroundDrawable.color
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, backGroundColor)
        colorAnimation.duration = 2000 // milliseconds
        colorAnimation.addUpdateListener { animator -> backgroundView.setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
    }


    override fun setBackgroundWithImage(activity: Activity) {
        val backgroundView = activity.findViewById<ImageSwitcher>(R.id.view_background)
        backgroundView.setImageResource(backGroundImage)
    }

    override fun setMainTextView(activity: Activity) {
        val mainTextView = activity.findViewById<TextSwitcher>(R.id.mainTextView)
        mainTextView.setText(stateMessage)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is StateImpl?)
            return false
        val otherState: StateImpl? = other
        return this.stateMessage == otherState?.stateMessage &&
                this.backGroundImage == otherState.backGroundImage &&
                this.song == otherState.song
    }

    override fun hashCode(): Int {
        var result = 17
        result = 31 * result + stateMessage.hashCode()
        result = 31 * result + backGroundImage.hashCode()
        result = 31 * result + song.hashCode()
        return result
    }

}