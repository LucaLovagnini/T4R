package com.example.llovagn.t4r.presenter

import com.example.llovagn.t4r.State
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.view.ViewMVC

class PresenterImpl(val view: ViewMVC, val model: Model) : Presenter {

    private fun updateView(state: State) {
        view.setMainTextView(state.getMessage())
    }

    override fun onClickBackground() {
        val modelState = model.getModelNextState()
        updateView(modelState)
    }

    override fun onClickPreviousButton() {
        val modelState = model.getModelPreviousState()
        updateView(modelState)
    }
}