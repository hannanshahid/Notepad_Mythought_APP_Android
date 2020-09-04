package com.example.mythought_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mythought_app.prefrences.intro_prefrence
import kotlinx.android.synthetic.main.activity_start_info_activity.*

class start_info_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_info_activity)
        nextbutton.setOnClickListener {
            if(editTextTextPersonName.text.toString().isEmpty())
            {
                editTextTextPersonName.setError("Thoughts belong to a person so your good name belongs to your thought")
            }
            else
            {
                var info_pref_obj= intro_prefrence(this)
                info_pref_obj.setstartinfostatus(true)
                info_pref_obj.setname(editTextTextPersonName.text.toString())
                var i: Intent = Intent(this,home_activity::class.java)
                startActivity(i)
                Toast.makeText(this,"Welcome ${editTextTextPersonName.text.toString()}",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}