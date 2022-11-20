package com.projects.neo.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment



open class BaseFragments :Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

    }

}