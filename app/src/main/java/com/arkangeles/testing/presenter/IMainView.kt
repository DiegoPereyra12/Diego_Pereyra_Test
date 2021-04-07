package com.arkangeles.testing.presenter

import androidx.fragment.app.Fragment

interface IMainView {

    fun loadFragment(fragment: Fragment?)
    fun showErrorToast()
}