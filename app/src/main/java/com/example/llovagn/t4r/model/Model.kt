package com.example.llovagn.t4r.model

import com.example.llovagn.t4r.State

interface Model {

    fun getModelState(): State
    fun getModelNextState(): State
    fun getModelPreviousState(): State

}