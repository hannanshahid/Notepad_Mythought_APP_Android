package com.example.mythought_app.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import nl.psdcompany.duonavigationdrawer.views.DuoOptionView


class Menuadapter(options: ArrayList<String>):BaseAdapter()
{
    private var mOptions: ArrayList<String> = options
    private val mOptionViews: ArrayList<DuoOptionView> = ArrayList()



    fun setViewSelected(position: Int, selected: Boolean) {

        // Looping through the options in the menu
        // Selecting the chosen option
        for (i in mOptionViews.indices) {
            if (i == position) {
                mOptionViews[i].isSelected = selected
            } else {
                mOptionViews[i].isSelected = !selected
            }
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val option = mOptions[position]!!

        // Using the DuoOptionView to easily recreate the demo

        // Using the DuoOptionView to easily recreate the demo
        val optionView: DuoOptionView = if (convertView == null)
        {
            DuoOptionView(parent!!.context)
        } else
        {
            convertView as DuoOptionView
        }
        // Using the DuoOptionView's default selectors

        // Using the DuoOptionView's default selectors
        optionView.bind(option, null, null)

        // Adding the views to an array list to handle view selection

        // Adding the views to an array list to handle view selection
        mOptionViews.add(optionView)

        return optionView
    }

    override fun getItem(position: Int): String? {
        return mOptions.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mOptions.size
    }
}