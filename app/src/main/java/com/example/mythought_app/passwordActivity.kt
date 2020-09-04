package com.example.mythought_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mythought_app.prefrences.intro_prefrence
import com.hanks.passcodeview.PasscodeView
import kotlinx.android.synthetic.main.activity_password.*

class passwordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        var intro_pref=intro_prefrence(this)
        passcode.setPasscodeLength(4)
            .setLocalPasscode(intro_pref.getpassword().toString())
            .setListener(object : PasscodeView.PasscodeViewListener{
                override fun onSuccess(number: String?) {
                    var i: Intent = Intent(this@passwordActivity, home_activity::class.java)
                    startActivity(i)
                    finish()
                }

                override fun onFail() {

                }

            })

    }
}