package com.gragica.gitinsight.data.network.dto

interface Dto<T> {
    fun toCoreModel(): T
}
