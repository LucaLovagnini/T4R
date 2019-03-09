package com.example.llovagn.t4r.model

import android.graphics.Color
import com.example.llovagn.t4r.State
import java.util.*

class CircularModel constructor(private var states: Deque<State>, private var repository: Repository<Deque<State>>) : Model {

    private val firstTimeMessage = "Welcome R"
    private val welcomeBackMessage = "Welcome Back, R"

    init {
        val loadedStates = repository.loadData()
        if (loadedStates != null) {
            states = loadedStates
            states.addFirst(State(welcomeBackMessage, 0, Color.BLUE))
        } else
            states.addFirst(State(firstTimeMessage, 0, Color.BLUE))
    }

    override fun getModelState(): State {
        return states.peekFirst()
    }

    override fun getModelNextState(): State {
        val state = states.removeFirst()
        if (state.getMessage() != firstTimeMessage && state.getMessage() != welcomeBackMessage)
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