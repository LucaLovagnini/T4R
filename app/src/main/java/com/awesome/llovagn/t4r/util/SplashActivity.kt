package com.awesome.llovagn.t4r.util

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.awesome.llovagn.t4r.R
import com.awesome.llovagn.t4r.view.MainActivity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.AlphaAnimation
import android.widget.ImageButton


class SplashActivity: AppCompatActivity() {

    private val touchScreenMessage = "Touch the screen to see the next Thing..."
    private val reverseButtonMessage = "You can also see Things again!"
    private val lastMessage = "All you have to do to start is to touch the screen..."
    private val goodByeMessage = "Enjoy the little Things!"
    private val splashPreferencesName = "splashPreferences"
    private val splashScreenExecutedKey = "firstTimeSplash"
    private val messageDuration = 5000L
    private val messages = arrayOf(
            "Welcome To\nThings For R",
            "In this app you're going to see a lot of Things",
            "Things can be messages, colors, images, sounds, anything!",
            touchScreenMessage,
            reverseButtonMessage,
            lastMessage
    )

    private var touchScreenMessageIndex: Int = 1
    private var isLastMessage = false
    private var sharedPreferences : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(splashPreferencesName, Context.MODE_PRIVATE)
        if(sharedPreferences!!.contains(splashScreenExecutedKey))
            executeMainActivity()

        setContentView(R.layout.activity_main)

        view_color_background.background = ColorDrawable(ResourcesCompat.getColor(resources, R.color.blueSea, null))

        // Disable actions when touching background
        // They will enabled when touchScreenMessage will be displayed
        view_color_background.setOnClickListener(null)
        view_background.setOnClickListener(null)

        // Setup TextSwitcher
        val `in` = AnimationUtils.loadAnimation(this, R.anim.fadein)
        val out = AnimationUtils.loadAnimation(this, R.anim.fadeout)

        mainTextView.inAnimation = `in`
        mainTextView.outAnimation = out

        mainTextView.setFactory {
            val textView = TextView(this@SplashActivity)
            textView.textSize = 25f
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setShadowLayer(30f, 0f, 0f, Color.GRAY)
            textView
        }

        // Hide image button and disable it
        imageButton.visibility = View.INVISIBLE
        imageButton.setOnClickListener(null)


        startSplashScreenActivity()
    }


    /**
     * The splash screen works as follow:
     * 1. Show the first message
     * 2. Every messageDuration milliseconds, show the next message, until touchScreenMessage is displayed
     * 3. Then, touchScreenAction is called every time that the screen is touched
     *
     * touchScreenMessageIndex is used as a starting point for messages displayed when screen is touched
     */
    private fun startSplashScreenActivity() {

        // Display first message
        mainTextView.setText(messages[0])

        for(i in 1 .. messages.size) {
            val message = messages[i]
            Handler().postDelayed(
                    {
                        mainTextView.setText(message)
                        if(message == touchScreenMessage) view_background.setOnClickListener {
                            touchScreenAction()
                        }
                    },
                    messageDuration*(i)
            )
            if(message == touchScreenMessage){
                touchScreenMessageIndex = i
                break
            }
        }
    }

    /**
     * When this function is called (i.e., when screen is touched):
     * 1. If the displayed message is lastMessage, then we run showGoodbyeMessageAndStartMainActivity
     * 2. Otherwise, display the next message.
     * 3. If the message is the reverseButtonMessage, then display the imageButton (though showImageButtonWithFade)
     */
    private fun touchScreenAction() {
        if(isLastMessage) showGoodbyeMessageAndStartMainActivity() else {
            val message = messages[++touchScreenMessageIndex]
            isLastMessage = message == lastMessage
            mainTextView.setText(message)

            if (message == reverseButtonMessage) showImageButtonWithFade(imageButton)
        }
    }

    private fun showImageButtonWithFade (button : ImageButton){
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        button.startAnimation(anim)
        button.visibility = View.VISIBLE
    }

    private fun showGoodbyeMessageAndStartMainActivity() {
        view_background.setOnClickListener(null)
        mainTextView.setText(goodByeMessage)
        Handler().postDelayed(
                {
                    setSplashScreenExecutedAndStartMainActivity()
                },
                messageDuration
        )
    }

    private fun setSplashScreenExecutedAndStartMainActivity() {
        // Set key in order to NOT execute the splash screen next time
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(splashScreenExecutedKey, true)
        editor.apply()

        // Start the main activity
        executeMainActivity()
    }

    private fun executeMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}