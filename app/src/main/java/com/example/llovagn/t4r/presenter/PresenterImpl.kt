package com.example.llovagn.t4r.presenter

import com.example.llovagn.t4r.State
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.view.ViewMVC

class PresenterImpl(val view: ViewMVC, private val model: Model) : Presenter {

    init {
        updateView(model.getModelState())
    }

    private fun updateView(state: State) {
        view.setMainTextView(state.getMessage())
        view.setBackgroundWithImage(state.getBackgroundImage())
        view.setBackgroundWithColor(state.getBackgroundColor())
    }

    override fun onClickBackground() {
        val nextState = model.getModelNextState()
        updateView(nextState)
    }

    override fun onClickPreviousButton() {
        val previousState = model.getModelPreviousState()
        updateView(previousState)
    }
}