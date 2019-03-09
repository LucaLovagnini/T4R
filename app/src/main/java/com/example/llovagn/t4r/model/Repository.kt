package com.example.llovagn.t4r.model

interface Repository<T> {
    fun saveData(obj: T)
    fun loadData(): T?
}