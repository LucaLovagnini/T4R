package com.example.llovagn.t4r.model

import com.example.llovagn.t4r.repository.Repository
import com.example.llovagn.t4r.state.State
import com.example.llovagn.t4r.util.sameStates
import java.util.*

class CircularModel constructor(private var states: Deque<State>, private var repository: Repository<Deque<State>>) : Model {

    init {
        val loadedStates = repository.loadData()
        if (loadedStates != null && sameStates(states, loadedStates))
            states = loadedStates
    }

    override fun getModelState(): State {
        return states.peekFirst()
    }

    override fun getModelNextState(): State {
        val state = states.removeFirst()
        states.addLast(state)
        repository.saveData(states)
        return getModelState()
    }

    override fun getModelPreviousState(): State {
        val state = states.removeLast()
        states.addFirst(state)
        repository.saveData(states)
        return getModelState()
    }
}