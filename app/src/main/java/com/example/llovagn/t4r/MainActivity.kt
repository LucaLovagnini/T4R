package com.example.llovagn.t4r

import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.presenter.Presenter
import com.example.llovagn.t4r.presenter.PresenterImpl
import com.example.llovagn.t4r.view.ViewMVC
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), ViewMVC {

    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: Model = CircularModel(LinkedList<State>(Arrays.asList(
                State("First"),
                State("Second"),
                State("Third"),
                State("Fourth"),
                State("Fifth")

        )))
        presenter = PresenterImpl(this, model)
    }

    override fun setBackgroundWithColor(androidColor: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackgroundWithImage(color: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMainTextView(message: String) {
        mainTextView.text = message
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
