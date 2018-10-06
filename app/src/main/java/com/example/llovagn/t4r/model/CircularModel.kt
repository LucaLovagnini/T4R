package com.example.llovagn.t4r.model

import com.example.llovagn.t4r.State
import java.util.*

class CircularModel constructor(val states: Deque<State>): Model {
    override fun getModelState() : State {
        return states.peekFirst()
    }

    override fun getModelNextState() : State {
        states.addLast(states.removeFirst())
        return states.first
    }

    override fun getModelPreviousState(): State {
        states.addFirst(states.removeLast())
        return states.first
    }
}