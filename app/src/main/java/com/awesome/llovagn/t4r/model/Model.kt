package com.awesome.llovagn.t4r.model

import com.awesome.llovagn.t4r.state.State

interface Model {

    fun getModelState(): State
    fun getModelNextState(): State
    fun getModelPreviousState(): State

}