package com.example.llovagn.t4r.model

import com.example.llovagn.t4r.State
import kotlinx.android.parcel.RawValue
import java.util.*

class CircularModel constructor(var states: @RawValue Deque<State>?) : Model {

    override fun getModelState(): State {
        return states!!.peekFirst()
    }

    override fun getModelNextState(): State {
        val state = states!!.removeFirst()
        states!!.addLast(state)
        return state
    }

    override fun getModelPreviousState(): State {
        val state = states!!.removeLast()
        states!!.addFirst(state)
        return state
    }
}