package com.example.llovagn.t4r.model

import android.os.Parcelable
import com.example.llovagn.t4r.State
import java.io.Serializable

interface Model : Parcelable, Serializable{

    fun getModelState(): State
    fun getModelNextState(): State
    fun getModelPreviousState(): State

}