package com.example.mythought_app.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.mythought_app.R
import com.example.mythought_app.database.Note_model_class
import com.example.mythought_app.database.database_helper
import kotlinx.android.synthetic.main.fragment_add_note_fragment.*
import kotlinx.android.synthetic.main.fragment_add_note_fragment.view.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Add_note_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Add_note_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
   var currretcolour:String=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       var v= inflater.inflate(R.layout.fragment_add_note_fragment, container, false)
        val sdf = SimpleDateFormat("dd MMM,yyyy hh:mm aa")
        val currentDate = sdf.format(Date())
        v.textViewdatetime.text=currentDate.toString().toUpperCase()
        currretcolour="#3d90e3"
        v.buttoncolour1_blue.setOnClickListener {
            v.cardView.setCardBackgroundColor(Color.parseColor("#3d90e3"))
            currretcolour="#3d90e3"
        }
        v.buttoncolour2_red.setOnClickListener {

            v.cardView.setCardBackgroundColor(Color.parseColor("#f54242"))
            currretcolour="#f54242"
        }
        v.buttoncolour3_yellow.setOnClickListener {
            v.cardView.setCardBackgroundColor(Color.parseColor("#e0c134"))
            currretcolour="#e0c134"
        }
        v.buttoncolour4_pink.setOnClickListener {
            v.cardView.setCardBackgroundColor(Color.parseColor("#e34ba6"))
            currretcolour="#e34ba6"

        }
        v.buttoncolour5_orange.setOnClickListener {
            v.cardView.setCardBackgroundColor(Color.parseColor("#e08d34"))
            currretcolour="#e08d34"
        }
        v.buttoncolour6_green.setOnClickListener {
            v.cardView.setCardBackgroundColor(Color.parseColor("#34e056"))
            currretcolour="#34e056"
        }

        v.buttondiscard.setOnClickListener {
           if(editTextnote.text.toString().isNotEmpty()) {
               var b = AlertDialog.Builder(context)
               b.setTitle("Discard")
               b.setMessage("Are you sure to Discard you writen thinking?")
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
                   v.cardView.setCardBackgroundColor(Color.parseColor("#3d90e3"))
                   currretcolour = "#3d90e3"
                   v.editTextnote.setText("")
                   a.dismiss()
               }
               bn.setOnClickListener {

                   a.dismiss()
               }
           }

        }
        v.buttonsave.setOnClickListener {
            if(editTextnote.text.toString().isNotEmpty())
            {
                var dbobject=database_helper(context)
                var note=Note_model_class(editTextnote.text.toString(),currentDate,currretcolour)
                dbobject.insert_note(note)
                v.cardView.setCardBackgroundColor(Color.parseColor("#3d90e3"))
                currretcolour = "#3d90e3"
                v.editTextnote.setText("")

            }
            else
            {
                Toast.makeText(context,"Please write something first!",Toast.LENGTH_LONG).show()
            }

        }
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Add_note_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Add_note_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}