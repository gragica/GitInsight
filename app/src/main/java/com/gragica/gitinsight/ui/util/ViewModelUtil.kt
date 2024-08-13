package com.gragica.gitinsight.ui.util

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.json.Json

inline fun <reified T> SavedStateHandle.getRoute(): T {
    val json = get<String>(T::class.java.name)
    return Json.decodeFromString(json!!)
}
