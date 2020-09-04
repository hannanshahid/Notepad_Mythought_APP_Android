package com.example.mythought_app.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mythought_app.R
import com.example.mythought_app.prefrences.intro_prefrence
import kotlinx.android.synthetic.main.fragment_setting_fragment.view.*
import kotlinx.android.synthetic.main.passcode_layout.view.*
import kotlinx.android.synthetic.main.username_set_layout.*
import kotlinx.android.synthetic.main.username_set_layout.view.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [setting_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class setting_fragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_setting_fragment, container, false)

         setusername(context,v)
        var intro_pref= context?.let { intro_prefrence(it) }
        var status=intro_pref?.getpasswordstatus()
        v.switchpassword.isChecked=status!!
        v.username_card_arrow.setOnClickListener {


            val builder = AlertDialog.Builder(context,R.style.usernameDialog)
            builder.setTitle("User Name")
            builder.setCancelable(false)

            val l = LayoutInflater.from(context)
            val x = l.inflate(R.layout.username_set_layout, null)
            builder.setView(x)
            builder.setPositiveButton("Save", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
            builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })


            val a = builder.create()
            a.show()
            x.usernametextbox.setText(intro_pref?.getname().toString())
            a.setCanceledOnTouchOutside(false)
            var bp: Button = a.getButton(AlertDialog.BUTTON_POSITIVE)
            var bn: Button = a.getButton(AlertDialog.BUTTON_NEGATIVE)
            bp.setOnClickListener {

                if (x.usernametextbox.text.toString().isEmpty() ) {
                    x.usernametextbox.setError("Please Enter name")
                }
                else
                {

                     intro_pref?.setname(x.usernametextbox.text.toString())
                     v.setting_name_textview.text=intro_pref?.getname()?.toUpperCase(Locale.ROOT)
                     a.dismiss()

                }
            }
            bn.setOnClickListener {

                a.dismiss()

            }


        }
        v.switchpassword.setOnClickListener {

            if(v.switchpassword.isChecked)
            {

                val builder = AlertDialog.Builder(context,R.style.usernameDialog)
                builder.setTitle("4 Digit Passcode")
                builder.setCancelable(false)

                val l = LayoutInflater.from(context)
                val x = l.inflate(R.layout.passcode_layout, null)
                builder.setView(x)
                builder.setPositiveButton("Save", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {}
                })
                builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {}
                })


                val a = builder.create()
                a.show()

                a.setCanceledOnTouchOutside(false)
                var bp: Button = a.getButton(AlertDialog.BUTTON_POSITIVE)
                var bn: Button = a.getButton(AlertDialog.BUTTON_NEGATIVE)
                bp.setOnClickListener {

                    if (x.passcodetextbox.toString().isEmpty() || x.passcodetextbox.length()<4 )
                    {
                        x.passcodetextbox.setError("Enter 4 digit Passcode")
                    }
                    else
                    {

                        intro_pref?.setpasswordstatus(true)
                        intro_pref?.setpassword(x.passcodetextbox.text.toString().trim().toInt())
                        a.dismiss()

                    }
                }
                bn.setOnClickListener {
                    intro_pref?.setpasswordstatus(false)
                    v.switchpassword.isChecked=false
                    a.dismiss()

                }

            }
            if(!v.switchpassword.isChecked)
            {
                intro_pref?.setpasswordstatus(false)

            }
        }
        return v
    }

    private fun setusername(context: Context?, v: View?) {
        var intro_pref= context?.let { intro_prefrence(it) }
        v?.setting_name_textview?.text=intro_pref?.getname()?.toUpperCase(Locale.ROOT)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment setting_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            setting_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}