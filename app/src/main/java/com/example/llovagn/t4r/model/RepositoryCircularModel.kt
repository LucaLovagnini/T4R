package com.example.llovagn.t4r.model

import android.app.Activity
import com.google.gson.Gson
import android.content.Context
import com.example.llovagn.t4r.State
import com.google.gson.reflect.TypeToken
import java.util.*


class RepositoryCircularModel constructor( activity: Activity) : Repository<Deque<State>> {

    private val storeFileName = "fileName"
    private val keyName = "keyName"
    private val sharedPreferences = activity.getSharedPreferences(storeFileName, Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    operator fun set(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    override fun saveData(obj: Deque<State>) {
        val gson = Gson()
        val json = gson.toJson(obj)

        set(keyName, json)
    }

    override fun loadData(): Deque<State>? {
        val serializedObject = sharedPreferences.getString(keyName, null)
        val gson = Gson()
        val type = object : TypeToken<Deque<State>>() {}.type
        return gson.fromJson(serializedObject, type)
    }

}