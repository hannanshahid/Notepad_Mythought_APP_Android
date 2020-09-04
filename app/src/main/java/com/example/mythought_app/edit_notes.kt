package com.example.mythought_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mythought_app.database.Note_model_class
import com.example.mythought_app.database.database_helper
import kotlinx.android.synthetic.main.activity_edit_notes.*
import kotlinx.android.synthetic.main.fragment_add_note_fragment.*
import kotlinx.android.synthetic.main.fragment_add_note_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

var note:String=""
var datetime:String=""
var colour:String=""
var id:Int=0
var currentcolour:String=""

class edit_notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notes)
        note=intent.getStringExtra("note")
        datetime=intent.getStringExtra("date")
        colour=intent.getStringExtra("colour")
        id=intent.getIntExtra("id",1)

        cardViewedit.setCardBackgroundColor(Color.parseColor(colour))
        currentcolour= colour
        editTextnoteedit.setText(note)
        textViewdatetimeedit.text= datetime

        val sdf = SimpleDateFormat("dd MMM,yyyy hh:mm aa")
        val currentDate = sdf.format(Date())
        buttoncolour1_blueedit.setOnClickListener {
            cardViewedit.setCardBackgroundColor(Color.parseColor("#3d90e3"))
            currentcolour="#3d90e3"
        }
        buttoncolour2_rededit.setOnClickListener {

            cardViewedit.setCardBackgroundColor(Color.parseColor("#f54242"))
            currentcolour="#f54242"
        }
        buttoncolour3_yellowedit.setOnClickListener {
            cardViewedit.setCardBackgroundColor(Color.parseColor("#e0c134"))
            currentcolour="#e0c134"
        }
        buttoncolour4_pinkedit.setOnClickListener {
            cardViewedit.setCardBackgroundColor(Color.parseColor("#e34ba6"))
            currentcolour="#e34ba6"

        }
        buttoncolour5_orangeedit.setOnClickListener {
            cardViewedit.setCardBackgroundColor(Color.parseColor("#e08d34"))
            currentcolour="#e08d34"
        }
        buttoncolour6_greenedit.setOnClickListener {
            cardViewedit.setCardBackgroundColor(Color.parseColor("#34e056"))
            currentcolour="#34e056"
        }

        buttondiscardedit.setOnClickListener {
            var b = AlertDialog.Builder(this)
            b.setTitle("Discard")
            b.setMessage("Are you sure to Discard?")
            b.setPositiveButton("yes", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
            b.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
            val a = b.create()
            a.show()
            a.setCanceledOnTouchOutside(false)
            var bp: Button = a.getButton(AlertDialog.BUTTON_POSITIVE)
            var bn: Button = a.getButton(AlertDialog.BUTTON_NEGATIVE)
            bp.setOnClickListener {

                a.dismiss()
                finish()
            }
            bn.setOnClickListener {

                a.dismiss()
            }
        }
        buttonsaveedit.setOnClickListener {

            if(editTextnoteedit.text.toString().isNotEmpty())
            {
                var dbobject= database_helper(this)
                var note= Note_model_class(editTextnoteedit.text.toString(), datetime, currentcolour)
                dbobject.update_note(id,note)


            }
            else
            {
                Toast.makeText(this,"note field is empty!", Toast.LENGTH_LONG).show()
            }
        }
    }
}