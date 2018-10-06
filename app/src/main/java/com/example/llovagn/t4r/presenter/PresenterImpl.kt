package com.example.llovagn.t4r.presenter

import com.example.llovagn.t4r.State
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.view.View
import java.util.*

class PresenterImpl(val view : View): Presenter {

    private val states : Deque<State> = LinkedList<State>(Arrays.asList(
            State("Second"), State("First")
    ))


    val model : Model = CircularModel(states)

    override fun onClickScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickPreviousButton() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}