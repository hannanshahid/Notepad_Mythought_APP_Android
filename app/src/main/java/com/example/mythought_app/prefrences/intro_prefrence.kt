package com.example.mythought_app.prefrences

import android.content.Context

class intro_prefrence(context: Context) {
    val PREFERENCE_NAME="introprefrence"
    val startinfo="startinfo"
    val name="name"
    val passwordstatus="passswordstatus"
    val password="password"

    val p=context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)

    fun getstartinfostatus():Boolean
    {
        return p.getBoolean(startinfo,false)
    }
    fun setstartinfostatus(status:Boolean)
    {
        val e=p.edit()
        e.putBoolean(startinfo,status)
        e.apply()
    }
    fun getname(): String?
    {
        return p.getString(name,"")
    }
    fun setname(Pname:String)
    {
        val e=p.edit()
        e.putString(name,Pname)
        e.apply()
    }
    fun getpasswordstatus(): Boolean
    {
        return p.getBoolean(passwordstatus,false)
    }
    fun setpasswordstatus(Pstatus:Boolean)
    {
        val e=p.edit()
        e.putBoolean(passwordstatus,Pstatus)
        e.apply()
    }
    fun getpassword():Int
    {
        return p.getInt(password,1234)
    }
    fun setpassword(pass:Int)
    {
        val e=p.edit()
        e.putInt(password,pass)
        e.apply()
    }
}