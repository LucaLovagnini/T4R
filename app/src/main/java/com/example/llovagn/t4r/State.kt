package com.example.llovagn.t4r

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextSwitcher
import com.bumptech.glide.Glide
import java.io.Serializable
import java.util.*

private fun randomColor(): Int {
    val rnd = Random()
    val random = Color.argb(255, rnd.nextInt(256),
            rnd.nextInt(256), rnd.nextInt(256))
    return random
}

class State(private val stateMessage: String,
                 private val backGroundImage: Int = 0,
                 private var backGroundColor: Int? = null) : Serializable {

    init {
        if (backGroundImage != 0)
            backGroundColor = Color.BLACK
    }

    fun execute(activity: Activity){
        setBackgroundWithColor(activity)
        setBackgroundWithImage(activity)
        setMainTextView(activity)
    }

    private fun setBackgroundWithColor(activity: Activity) {
        val backgroundView= activity.findViewById<View>(R.id.view_color_background)
        if(backGroundColor == null || backGroundColor == 0) backGroundColor = randomColor()
        val colorBackgroundDrawable = backgroundView.background as ColorDrawable
        val colorFrom = colorBackgroundDrawable.color
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, backGroundColor)
        colorAnimation.duration = 2000 // milliseconds
        colorAnimation.addUpdateListener { animator -> backgroundView.setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
    }


    private fun setBackgroundWithImage(activity: Activity) {
        val backgroundView= activity.findViewById<ImageSwitcher>(R.id.view_background)
        backgroundView.setImageResource(backGroundImage)
    }

    private fun setMainTextView(activity : Activity) {
        val mainTextView= activity.findViewById<TextSwitcher>(R.id.mainTextView)
        mainTextView.setText(stateMessage)
    }
    fun playSong(state: State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMessage(): String {
        return stateMessage
    }

}