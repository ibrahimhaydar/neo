package com.projects.neo.data.repository.pref


import com.projects.neo.data.repository.SessionRepository
import com.projects.neo.data.util.PrefUtils
import com.projects.neo.data.util.preferences.RxPrefProperty

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefSessionRepository
@Inject
constructor(prefUtils: PrefUtils) : SessionRepository {
    override var language: String by RxPrefProperty(prefUtils.language)
    override var localize: String by RxPrefProperty(prefUtils.localize)
}