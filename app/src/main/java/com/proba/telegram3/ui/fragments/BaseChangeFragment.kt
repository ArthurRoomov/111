package com.proba.telegram3.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.proba.telegram3.MainActivity

import com.proba.telegram3.R
import com.proba.telegram3.utilits.APP_ACTIVITY
import com.proba.telegram3.utilits.hideKeyboard


open class BaseChangeFragment (layout:Int): Fragment(layout) {

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        (activity as MainActivity).mAppDrawer.disableDrawer()
        hideKeyboard()
    }

    override fun onStop() {
        super.onStop()

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_confirm_change -> change()
        }
        return true
    }

    open fun change() {


    }
}
