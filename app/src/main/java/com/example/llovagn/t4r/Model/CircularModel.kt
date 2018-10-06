package com.example.llovagn.t4r.Model

import com.example.llovagn.t4r.State
import java.util.*

class CircularModel constructor(states: Deque<State>): Model(states) {
    override fun getModelState() : State {
        return states.peekFirst()
    }

    override fun getModelNextState() : State {
        states.addLast(states.first)
        return states.first
    }
}