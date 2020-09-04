package com.example.mythought_app.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mythought_app.R
import com.example.mythought_app.adapters.NoteAdapter
import com.example.mythought_app.database.database_helper
import kotlinx.android.synthetic.main.activity_home_activity.*
import kotlinx.android.synthetic.main.fragment_home_fragment.*
import kotlinx.android.synthetic.main.fragment_home_fragment.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home_fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home_fragment : Fragment() {
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


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_home_fragment, container, false)

        val  lm= LinearLayoutManager(context)
        lm.orientation = RecyclerView.VERTICAL
        v.notesrecyclerview.layoutManager=lm


        filldata(v)
      v.homeconstrain.setOnTouchListener{ view: View, motionEvent: MotionEvent ->
          val imm =
              activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
          imm!!.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
      }

        return v
    }

    private fun filldata(v: View?) {
        var dbobject=database_helper(context)
        var list=dbobject.read_data()
        if(list.isNotEmpty()) {
            var ad = NoteAdapter(context, list)
            v!!.notesrecyclerview.adapter = ad
            v!!.nonote.visibility=View.INVISIBLE

        }
        else
        {
            var ad = NoteAdapter(context, list)
            v!!.notesrecyclerview.adapter = ad
            v!!.nonote.visibility=View.VISIBLE
        }

    }



    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
       if(hidden)
       {

       }
        else
        {
            var dbobject=database_helper(context)
            var list=dbobject.read_data()
            if(list.isNotEmpty()) {
                var ad = NoteAdapter(context, list)
                 notesrecyclerview.adapter = ad
                runLayoutAnimation(notesrecyclerview)
                nonote.visibility=View.INVISIBLE

            }
            else
            {
                var ad = NoteAdapter(context, list)
                notesrecyclerview.adapter = ad
                runLayoutAnimation(notesrecyclerview)
                nonote.visibility=View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        var dbobject=database_helper(context)
        var list=dbobject.read_data()
        if(list.isNotEmpty()) {
            var ad = NoteAdapter(context, list)
            notesrecyclerview.adapter = ad
            runLayoutAnimation(notesrecyclerview)
            nonote.visibility=View.INVISIBLE

        }
        else
        {
            var ad = NoteAdapter(context, list)
            notesrecyclerview.adapter = ad
            runLayoutAnimation(notesrecyclerview)
            nonote.visibility=View.VISIBLE
        }
    }
    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context: Context = recyclerView.context
        val controller =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home_fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home_fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}