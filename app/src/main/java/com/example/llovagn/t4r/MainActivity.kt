package com.example.llovagn.t4r

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.llovagn.t4r.R.drawable.bojack
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.model.RepositoryCircularModel
import com.example.llovagn.t4r.presenter.Presenter
import com.example.llovagn.t4r.presenter.PresenterImpl
import com.example.llovagn.t4r.view.ViewInter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), ViewInter {

    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                State("First", bojack),
                State("Second"),
                State("Third")))

        val repositoryCircularModel = RepositoryCircularModel(this)
        val model: Model? = CircularModel(states,
                repositoryCircularModel
        )

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

        presenter = PresenterImpl(this, model!!)
    }

    fun onTouchBackground(view: View) {
        presenter.onClickBackground()
    }

    fun onTouchPreviousThing(view: View) {
        presenter.onClickPreviousButton()
    }

    override fun executeState(state: State) {
        state.execute(this)
    }
}
