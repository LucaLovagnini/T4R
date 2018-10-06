package com.example.llovagn.t4r.view

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.llovagn.t4r.R
import com.example.llovagn.t4r.State
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.presenter.PresenterImpl
import java.util.*

class MainActivity : AppCompatActivity(), View {

    private val presenter = PresenterImpl(this)

    override fun setBackgroundWithColor(androidColor: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackgroundWithImage(color: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMainTextView(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playSong(state: State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val states : Deque<State> = LinkedList<State>()
        states.add(State("Hello world"))
    }


}
