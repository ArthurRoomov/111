package com.proba.telegram3.ui.fragments

import androidx.fragment.app.Fragment
import com.proba.telegram3.MainActivity

import com.proba.telegram3.R
import com.proba.telegram3.activities.RegisterActivity
import com.proba.telegram3.utilits.AUTH
import com.proba.telegram3.utilits.replaceActivity
import com.proba.telegram3.utilits.replaceFragment
import com.proba.telegram3.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber:String
    private lateinit var mCallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    showToast("Добро пожаловать")
                    (activity as RegisterActivity).replaceActivity(MainActivity())
                } else showToast(task.exception?.message.toString())
            }
        }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
        }
        register_btn_next.setOnClickListener {sendCode()}    }

    private fun sendCode() {
        if (register_input_phone_number.text.toString().isEmpty()){
            showToast(getString(R.string.register_toast_enter_phone))
        } else {

            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )

    }
}
