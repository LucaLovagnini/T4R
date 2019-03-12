package com.awesome.llovagn.t4r.presenter

import com.awesome.llovagn.t4r.model.Model
import com.awesome.llovagn.t4r.state.State
import com.awesome.llovagn.t4r.view.ViewInter

class PresenterImpl(private val view: ViewInter, private val model: Model) : Presenter {

    init {
        updateView(model.getModelState())
    }

    private fun updateView(state: State) {
        view.executeState(state)
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