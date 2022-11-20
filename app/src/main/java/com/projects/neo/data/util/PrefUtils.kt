package com.projects.neo.data.util

import com.f2prateek.rx.preferences2.RxSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefUtils
@Inject
constructor(preferences: RxSharedPreferences) {
    val language = preferences.getString("pref_language", "en")
    val localize = preferences.getString("localize", "")
}
