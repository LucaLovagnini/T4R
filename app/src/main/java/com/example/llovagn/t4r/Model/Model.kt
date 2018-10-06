package com.example.llovagn.t4r.Model

import com.example.llovagn.t4r.State
import java.util.*

abstract class Model constructor(val states: Deque<State>) {

    abstract fun getModelState(): State
    abstract fun getModelNextState(): State


}