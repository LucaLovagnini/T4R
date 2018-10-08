package com.example.llovagn.t4r

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.presenter.Presenter
import com.example.llovagn.t4r.presenter.PresenterImpl
import com.example.llovagn.t4r.view.ViewMVC
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

class MainActivity : AppCompatActivity(), ViewMVC {

    private lateinit var presenter: Presenter
    private var model: Model? = CircularModel(LinkedList<State>(Arrays.asList(
            State("First", R.drawable.bojack),
            State("Second"),
            State("Third"),
            State("Fourth"),
            State("Fifth")

    )))

    private val serialFileName = "model.ser"

    private fun doesSerializableExist(): Boolean {
        val context = applicationContext;
        val file = context.getFileStreamPath(serialFileName)

        return file == null || !file.exists()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val `in` = AnimationUtils.loadAnimation(this, R.anim.fadein)
        val out = AnimationUtils.loadAnimation(this, R.anim.fadeout)

        mainTextView.inAnimation = `in`
        mainTextView.outAnimation = out

        view_background.inAnimation = `in`
        view_background.outAnimation = out

        mainTextView.setFactory {
            val textView = TextView(this@MainActivity)
            textView.textSize = 36f
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setShadowLayer(30f, 0f, 0f, Color.GRAY);
            textView
        }

        view_background.setFactory {
            val imageView = ImageView(this@MainActivity)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView
        }

        if (savedInstanceState != null) {
            model = savedInstanceState.getParcelable("model")
        }
        presenter = PresenterImpl(this, model!!)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("model", model!!)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()

        val context = applicationContext;

        val fos = context.openFileOutput(serialFileName, Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(model)
        os.close()
        fos.close()
    }

    override fun onResume() {
        super.onResume()

        if (doesSerializableExist()) {
            return
        }

        val fis = applicationContext.openFileInput(serialFileName)
        val `is` = ObjectInputStream(fis)
        model = `is`.readObject() as Model
        presenter = PresenterImpl(this, model!!)
        `is`.close()
        fis.close()

    }

    override fun setBackgroundWithColor(androidColor: Int) {
        val colorBackgroundDrawable = view_color_background.background as ColorDrawable
        val colorFrom = colorBackgroundDrawable.color
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, androidColor)
        colorAnimation.duration = 2000 // milliseconds
        colorAnimation.addUpdateListener { animator -> view_color_background.setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
    }

    override fun setBackgroundWithImage(image: Int) {
        view_background.setImageResource(image)
    }

    override fun setMainTextView(message: String) {
        mainTextView.setText(message)
    }

    override fun playSong(state: State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onTouchBackground(view: View) {
        presenter.onClickBackground()
    }

    fun onTouchPreviousThing(view: View) {
        presenter.onClickPreviousButton()
    }
}
