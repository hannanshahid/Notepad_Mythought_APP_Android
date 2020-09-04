package com.example.mythought_app.adapters

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mythought_app.R
import com.example.mythought_app.database.Note_model_class
import com.example.mythought_app.database.database_helper
import com.example.mythought_app.edit_notes

import kotlinx.android.synthetic.main.note_cardview.view.*


class NoteAdapter(val context: Context?, var list: MutableList<Note_model_class>):RecyclerView.Adapter<NoteAdapter.myviewholder>()
{

    inner class myviewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        @SuppressLint("SetTextI18n")
        fun setdqata(h: Note_model_class,p:Int) {
            itemView.textViewnote.text=h.note
            itemView.textViewdate.text="Created at: ${h.datetime}"
            itemView.background.setTint(Color.parseColor("${h.colour}"))
            itemView.imageViewshare.setOnClickListener {
                var d="${h.note}\n\n created at : ${h.datetime}"
                var i = Intent()
                i.action = Intent.ACTION_SEND
                i.putExtra(Intent.EXTRA_TEXT, d)
                i.setType("text/plain")
                context!!.startActivity(Intent.createChooser(i, "Share To: "))
            }
            itemView.imageViewcopy.setOnClickListener {
                var myClipboard = context!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                var myClip: ClipData = ClipData.newPlainText("note_copy", h.note)
                myClipboard.setPrimaryClip(myClip)
                Toast.makeText(context,"Note copy to Clipboard",Toast.LENGTH_SHORT).show()
            }
            itemView.imageViewwrite.setOnClickListener {


                val i=Intent(context,edit_notes::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("note","${h.note}")
                i.putExtra("date","${h.datetime}")
                i.putExtra("colour","${h.colour}")
                i.putExtra("id",h.id)
                context!!.startActivity(i)
            }
            itemView.textViewnote.setOnClickListener {
                val i=Intent(context,edit_notes::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                i.putExtra("note","${h.note}")
                i.putExtra("date","${h.datetime}")
                i.putExtra("colour","${h.colour}")
                i.putExtra("id",h.id)
                context!!.startActivity(i)

            }
            itemView.imageViewdelete.setOnClickListener {
                var db=database_helper(context)
                db.delete_note(h.id)

                list.remove(h)
                notifyDataSetChanged()





            }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        val view= LayoutInflater.from(context).inflate(R.layout.note_cardview,parent,false)
        return myviewholder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val h=list[position]
        holder.setdqata(h,position)
    }

}