package com.example.llovagn.t4r.model

import android.os.Parcelable
import com.example.llovagn.t4r.State
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
class CircularModel constructor(var states: @RawValue Deque<State>?) : Model, Parcelable {

    override fun getModelState(): State {
        return states!!.peekFirst()
    }

    override fun getModelNextState(): State {
        val state = states!!.removeFirst()
        states!!.addLast(state)
        return getModelState()
    }

    override fun getModelPreviousState(): State {
        val state = states!!.removeLast()
        states!!.addFirst(state)
        return getModelState()
    }
}