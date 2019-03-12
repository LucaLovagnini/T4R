package com.awesome.llovagn.t4r

import com.awesome.llovagn.t4r.state.State
import com.awesome.llovagn.t4r.state.StateImpl
import com.awesome.llovagn.t4r.util.sameStates
import org.junit.Assert.assertFalse
import org.junit.Test
import java.util.*

class StateUtil {

    private val messageState = StateImpl("Hello")
    private val anotherMessageState = StateImpl("World")
    private val messageAndBackgroundState = StateImpl("Hello", 1)
    private val completeState = StateImpl("Hello", 1, 2)

    @Test
    fun sameStatesSameOrder() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                messageState, anotherMessageState, messageAndBackgroundState, completeState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageState, anotherMessageState, messageAndBackgroundState, completeState
        ))
        assert(sameStates(states, otherStates))
    }

    @Test
    fun sameStatesDifferentOrder() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                anotherMessageState, messageAndBackgroundState, messageState, completeState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageState, anotherMessageState, messageAndBackgroundState, completeState
        ))
        assert(sameStates(states, otherStates))
    }

    @Test
    fun sameStatesDifferentMessages() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState, completeState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, completeState, anotherMessageState
        ))
        assertFalse(sameStates(states, otherStates))
    }

    @Test
    fun sameStatesFirstLonger() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState, completeState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState
        ))
        assertFalse(sameStates(states, otherStates))
    }

    @Test
    fun sameStatesSecondLonger() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState, completeState
        ))
        assertFalse(sameStates(states, otherStates))
    }

    @Test
    fun sameStatesDifferentOccurrences() {
        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState
        ))
        val otherStates: Deque<State> = LinkedList<State>(Arrays.asList(
                messageAndBackgroundState, messageState, messageState
        ))
        assertFalse(sameStates(states, otherStates))
    }
}