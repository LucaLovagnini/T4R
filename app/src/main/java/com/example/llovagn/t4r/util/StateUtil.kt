package com.example.llovagn.t4r.util

import com.example.llovagn.t4r.state.State
import java.util.*

fun sameStates(states: Iterable<State>, otherStates: Iterable<State>): Boolean {
    val statesOccurrences: HashMap<State, Int> = HashMap()
    states.forEach {
        if (statesOccurrences.containsKey(it))
            statesOccurrences[it] = statesOccurrences[it]!! + 1
        else
            statesOccurrences[it] = 1
    }
    otherStates.forEach {
        if (!statesOccurrences.containsKey(it))
            return false
        else
            statesOccurrences[it] = statesOccurrences[it]!! - 1
    }
    statesOccurrences.forEach {
        if (it.value != 0)
            return false
    }
    return true
}
