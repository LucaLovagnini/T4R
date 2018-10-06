package com.example.llovagn.t4r.model

import com.example.llovagn.t4r.State
import org.junit.Test

import org.junit.Assert.*
import java.util.*

const val FIRST = "first"
const val SECOND = "second"

class CircularModelTest {

    private val firstState = State(FIRST)
    private val states : Deque<State> = LinkedList<State>(Arrays.asList(
            firstState, State(SECOND)
    ))

    @Test
    fun getFirstModelState() {
        val circularModel = CircularModel(states)
        assertEquals(circularModel.getModelState(), firstState)
    }

    @Test
    fun getSecondModelState() {
        val circularModel = CircularModel(states)
        val secondState = circularModel.getModelNextState()
        assertEquals(circularModel.getModelState(), secondState)
    }

    @Test
    fun getCircularState() {
        val circularModel = CircularModel(states)
        val firstState = circularModel.getModelState()
        circularModel.getModelNextState()
        assertEquals(circularModel.getModelNextState(), firstState)
    }
}