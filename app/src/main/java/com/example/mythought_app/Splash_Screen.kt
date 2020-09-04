package com.example.mythought_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.mythought_app.prefrences.intro_prefrence
import kotlinx.android.synthetic.main.activity_main.*

class Splash_Screen : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 6000 //6 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            var info_pref_obj=intro_prefrence(this)
            var status=info_pref_obj.getstartinfostatus()
            var passstatus=info_pref_obj.getpasswordstatus()
            if(!status)
            {
                var i: Intent = Intent(this, start_info_activity::class.java)
                startActivity(i)
                finish()
            }
            else if(passstatus)
            {

                var i: Intent = Intent(this, passwordActivity::class.java)
                startActivity(i)
                finish()
            }
            else
            {
                var i: Intent = Intent(this, home_activity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadinganim.visibility= View.VISIBLE
        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }
    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}