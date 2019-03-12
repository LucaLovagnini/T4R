package com.awesome.llovagn.t4r.repository

import android.app.Activity
import android.content.Context
import com.awesome.llovagn.t4r.state.State
import com.awesome.llovagn.t4r.state.StateImpl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RepositoryStateImplCircularModel constructor(activity: Activity) : Repository<Deque<State>> {

    private val storeFileName = "fileName"
    private val keyName = "keyName"
    private val sharedPreferences = activity.getSharedPreferences(storeFileName, Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    operator fun set(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override fun saveData(obj: Deque<State>) {
        val states: Deque<StateImpl> = obj as Deque<StateImpl>
        val gson = Gson()
        val json = gson.toJson(states)

        set(keyName, json)
    }

    override fun loadData(): Deque<State>? {
        val serializedObject = sharedPreferences.getString(keyName, null)
        val gson = Gson()
        val type = object : TypeToken<Deque<StateImpl>>() {}.type
        val states: Deque<StateImpl>? = gson.fromJson(serializedObject, type)
        return states as Deque<State>?
    }

}