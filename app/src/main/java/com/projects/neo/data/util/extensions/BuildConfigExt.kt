package com.projects.neo.data.util.extensions

import com.projects.neo.BuildConfig

inline fun debugOnly(block: () -> Unit) {
    if (BuildConfig.DEBUG) {
        block()
    }
}

inline fun releaseOnly(block: () -> Unit) {
    if (!BuildConfig.DEBUG) {
        block()
    }
}

//inline fun needsUpdate(serverVersion: Int): Boolean = serverVersion > BuildConfig.VERSION_CODE