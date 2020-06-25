package com.proba.telegram3.ui.fragments

import androidx.fragment.app.Fragment
import com.proba.telegram3.R
import com.proba.telegram3.utilits.APP_ACTIVITY


class ChatsFragment : Fragment(R.layout.fragment_chats) {


    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Чаты"

    }
}
