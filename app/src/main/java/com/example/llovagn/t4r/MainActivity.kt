package com.example.llovagn.t4r

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.llovagn.t4r.Model.CircularModel
import com.example.llovagn.t4r.Model.Model
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val states : Deque<State> = LinkedList<State>()
        states.add(State("Hello world"))
    }


}
