package com.example.llovagn.t4r.view

import com.example.llovagn.t4r.state.State

interface ViewInter {

    fun executeState(state: State)
    fun playSong(song: Int)

}