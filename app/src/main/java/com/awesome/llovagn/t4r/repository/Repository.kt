package com.awesome.llovagn.t4r.repository

interface Repository<T> {
    fun saveData(obj: T)
    fun loadData(): T?
}