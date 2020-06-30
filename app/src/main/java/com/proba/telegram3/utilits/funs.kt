package com.proba.telegram3.utilits

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.proba.telegram3.MainActivity
import com.proba.telegram3.R
import com.proba.telegram3.database.updatePhonesToDatabase
import com.proba.telegram3.models.CommonModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun showToast(message:String){
    Toast.makeText(APP_ACTIVITY,message,Toast.LENGTH_SHORT).show()
}

fun restartActivity(){
    val intent = Intent(APP_ACTIVITY,MainActivity::class.java)
    APP_ACTIVITY.startActivity(intent)
    APP_ACTIVITY.finish()
}

fun replaceFragment(fragment: Fragment, addStack:Boolean = true){
        if (addStack){
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.data_container,
            fragment
        ).commit()
} else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(R.id.data_container,
                fragment
            ).commit()
        }
}


fun hideKeyboard() {
    val imm: InputMethodManager =
        APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken, 0)
}

fun ImageView.downloadAndSetImage (url:String){
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.default_photo)
        .into(this)
}

fun initContacts() {
    if (checkPermission(READ_CONTACTS)) {
        var arrayContacts = arrayListOf<CommonModel>()
        val cursor = APP_ACTIVITY.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.let {
            while (it.moveToNext()) {
                val fullName =
                    it.getString(it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val phone =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val newModel = CommonModel()
                newModel.fullname = fullName
                newModel.phone = phone.replace(Regex("[\\s,-]"), "")
                arrayContacts.add(newModel)
            }
        }
        cursor?.close()
        updatePhonesToDatabase(arrayContacts)
    }
}

fun String.asTime(): String {
    val time = java.util.Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}