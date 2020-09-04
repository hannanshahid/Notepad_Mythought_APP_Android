package com.example.mythought_app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.util.*

val DATABASE_NAME="mythinking_DB"
val TABLE_NAME="Notes"
val COL_ID="id"
val COL_note="note"
val COL_Date_time="dateandtime"
val COL_colour="colour"
class database_helper(var context:Context?):SQLiteOpenHelper(context, DATABASE_NAME,null,1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val createtable="CREATE TABLE "+ TABLE_NAME +" ("+ COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_note +" VARCHAR(1000), "+ COL_Date_time + " VARCHAR(50), "+ COL_colour +" VARCHAR(50))";

        db?.execSQL(createtable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert_note(note:Note_model_class)
    {
        val db=this.writableDatabase
        var cv= ContentValues()
        cv.put(COL_note,note.note)
        cv.put(COL_Date_time,note.datetime)
        cv.put(COL_colour,note.colour)
        val result=db.insert(TABLE_NAME,null,cv)
        if(result== -1.toLong())
        {
            Toast.makeText(context,"Fail to store Note", Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(context,"your note is Saved", Toast.LENGTH_LONG).show()
        }
        db.close()
    }
    fun read_data():MutableList<Note_model_class>
    {
        var list : MutableList<Note_model_class> = ArrayList()
        val db=this.readableDatabase
        var query="select * from "+ TABLE_NAME+" order by $COL_ID DESC";
        var result=db.rawQuery(query,null)
        if(result.moveToFirst())
        {
            do {

                var condition=Note_model_class()
                condition.id=result.getString(result.getColumnIndex(COL_ID)).toInt()
                condition.note=result.getString(result.getColumnIndex(COL_note))
                condition.datetime=result.getString(result.getColumnIndex(COL_Date_time))
                condition.colour=result.getString(result.getColumnIndex(COL_colour))
                list.add(condition)
            }
            while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun update_note( id:Int,note:Note_model_class)
    {
        val db=this.writableDatabase
        var cv= ContentValues()
        cv.put(COL_note,note.note)
        cv.put(COL_Date_time,note.datetime)
        cv.put(COL_colour,note.colour)
        val whereclaus="$COL_ID=?"
        val wherearg= arrayOf(id.toString())
        val result=db.update(TABLE_NAME,cv,whereclaus,wherearg)

            Toast.makeText(context,"updated", Toast.LENGTH_LONG).show()

        db.close()
    }
    fun delete_note( id:Int)
    {
        val db=this.writableDatabase
        val whereclaus="$COL_ID=?"
        val wherearg= arrayOf(id.toString())
        db.delete(TABLE_NAME,whereclaus,wherearg)
          Log.i("dele","deleteing row $id")
        db.close()

    }

}