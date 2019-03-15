package com.awesome.llovagn.t4r

import android.widget.ImageSwitcher
import android.widget.TextSwitcher
import com.awesome.llovagn.t4r.state.StateImpl
import com.awesome.llovagn.t4r.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.mockito.Mockito.*


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class StateImplTest {

    var mockedActivity: MainActivity = mock(MainActivity::class.java)
    var mockedImageSwithcer = mock(ImageSwitcher::class.java)
    var mockedTextSwitcher = mock(TextSwitcher::class.java)

    val message = "Hello World!"

    private fun assertHashCodes(state1: StateImpl, state2: StateImpl, equals: Boolean) {
        assertEquals(state1.hashCode() == state2.hashCode(), equals)
    }

    @Test
    fun equalStatesWithoutColors() {
        val state1 = StateImpl(message, 1, 2)
        val state2 = StateImpl(message, 1, 2)
        assertEquals(state1, state2)
        assertHashCodes(state1, state2, true)
    }

    @Test
    fun equalStatesWithOneColor() {
        val state1 = StateImpl(message, 1, 2)
        val state2 = StateImpl(message, 1, 2, 3)
        assertHashCodes(state1, state2, true)
    }

    @Test
    fun equalStatesWithDifferentColors() {
        val state1 = StateImpl(message, 1, 2, 3)
        val state2 = StateImpl(message, 1, 2, 4)
        assertEquals(state1, state2)
        assertHashCodes(state1, state2, true)

    }

    @Test
    fun equalStatesWithDifferentMessages() {
        val state1 = StateImpl("HELLOWORLD")
        val state2 = StateImpl(message)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithOneBackground() {
        val state1 = StateImpl(message, 1)
        val state2 = StateImpl(message)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithDifferentBackgrounds() {
        val state1 = StateImpl(message, 1)
        val state2 = StateImpl(message, 2)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithOneSong() {
        val state1 = StateImpl(message, 1, 3)
        val state2 = StateImpl(message, 2)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalStatesWithDifferentSongs() {
        val state1 = StateImpl(message, 1, 3)
        val state2 = StateImpl(message, 2, 4)
        assertNotEquals(state1, state2)
        assertHashCodes(state1, state2, false)
    }

    @Test
    fun equalsStatesDifferentObjects() {
        val state1 = StateImpl(message)
        assertNotEquals(state1, message)
    }

    @Test
    fun playSongTest() {
        val song = 10
        val state = StateImpl(message, 0, song)
        state.playSong(mockedActivity)
        verify(mockedActivity, times(1)).playSong(song)
        verifyNoMoreInteractions(mockedActivity)
    }

    @Test
    fun setBackgroundWithImageTest() {
        val backgroundImage = 10
        val state = StateImpl(message, backgroundImage)
        `when`(mockedActivity.findViewById<ImageSwitcher>(R.id.view_background)).thenReturn(mockedImageSwithcer)
        state.setBackgroundWithImage(mockedActivity)
        verify(mockedImageSwithcer, times(1)).setImageResource(backgroundImage)
    }

    @Test
    fun setMainTextViewTest() {
        val state = StateImpl(message)
        `when`(mockedActivity.findViewById<TextSwitcher>(R.id.mainTextView)).thenReturn(mockedTextSwitcher)
        state.setMainTextView(mockedActivity)
        verify(mockedTextSwitcher, times(1)).setText(message)
    }

}
