package com.example.llovagn.t4r

import com.example.llovagn.t4r.state.StateImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StateImplTest {

    private fun assertHashCodes(state1: StateImpl, state2: StateImpl, equals: Boolean) {
        assertEquals(state1.hashCode() == state2.hashCode(), equals)
    }

    @Test
    fun equalStatesWithoutColors() {
        val state1 = StateImpl("hello world", 1, 2)
        val state2 = StateImpl("hello world", 1, 2)
        assertEquals(state1, state2)
        assertHashCodes(state1, state2, true)
    }

    @Test
    fun equalStatesWithOneColor() {
        val state1 = StateImpl("hello world", 1, 2)
        val state2 = StateImpl("hello world", 1, 2, 3)
        assertHashCodes(state1, state2, true)
    }

    @Test
    fun equalStatesWithDifferentColors() {
        val state1 = StateImpl("hello world", 1, 2, 3)
        val state2 = StateImpl("hello world", 1, 2, 4)
        assertEquals(state1, state2)
        assertHashCodes(state1, state2, true)

    }

    @Test
    fun equalStatesWithDifferentMessages() {
        val state1 = StateImpl("HELLOWORLD")
        val state2 = StateImpl("hello world")
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithOneBackground() {
        val state1 = StateImpl("hello world", 1)
        val state2 = StateImpl("hello world")
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithDifferentBackgrounds() {
        val state1 = StateImpl("hello world", 1)
        val state2 = StateImpl("hello world", 2)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithOneSong() {
        val state1 = StateImpl("hello world", 1, 3)
        val state2 = StateImpl("hello world", 2)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithDifferentSongs() {
        val state1 = StateImpl("hello world", 1, 3)
        val state2 = StateImpl("hello world", 2, 4)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalsStatesDifferentObjects() {
        val state1 = StateImpl("hello world")
        assertNotEquals(state1, "hello world")
    }

}
