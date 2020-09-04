package com.example.mythought_app


import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mythought_app.adapters.Menuadapter
import com.example.mythought_app.fragments.Add_note_fragment
import com.example.mythought_app.fragments.Home_fragment
import com.example.mythought_app.fragments.aboutus_fragment
import com.example.mythought_app.fragments.setting_fragment
import com.example.mythought_app.prefrences.intro_prefrence
import kotlinx.android.synthetic.main.activity_home_activity.*
import kotlinx.android.synthetic.main.duo_view_footer.*
import kotlinx.android.synthetic.main.duo_view_header.view.*
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle
import java.security.AccessController.getContext
import java.util.*
import kotlin.collections.ArrayList


class home_activity : AppCompatActivity(), DuoMenuView.OnMenuClickListener
{
    lateinit var tollbar:Toolbar
    lateinit var duomenuadpater:Menuadapter
    lateinit var  dlayout:DuoDrawerLayout
    lateinit var dtoggle:DuoDrawerToggle
    lateinit var currentfrag:Fragment
     var homefrag=Home_fragment()
    var adnotefrag=Add_note_fragment()
    var aboutusfrag=aboutus_fragment()
    var settingfrag=setting_fragment()


    public val mTitles: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activity)
        mTitles.add("Home")
        mTitles.add("Add New Note")
        mTitles.add("Settings")
        mTitles.add("About us")

        // set drawer
         dlayout=findViewById(R.id.drawer)
        dtoggle= DuoDrawerToggle(this,dlayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        dlayout.setDrawerListener(dtoggle)
        dtoggle.syncState()
        ////////////


        var fm=supportFragmentManager
        fm.beginTransaction().add(R.id.containers,settingfrag,"4").commit()
        fm.beginTransaction().hide(settingfrag).commit()
        fm.beginTransaction().add(R.id.containers,aboutusfrag,"3").commit()
        fm.beginTransaction().hide(aboutusfrag).commit()
        fm.beginTransaction().add(R.id.containers,adnotefrag,"2").commit()
        fm.beginTransaction().hide(adnotefrag).commit()
        fm.beginTransaction().add(R.id.containers,homefrag,"1").commit()
        currentfrag=homefrag

        handleMenu()

        var info_pref_obj= intro_prefrence(this)
        duomenuadpater.setViewSelected(0,true)
        setheadername()
       textViewtoolbar.setText("Thought's")
        imageViewfacebook.setOnClickListener {
            openfacebook()

        }
        imageViewwhatsapp.setOnClickListener {
            openwhatsapp()
        }
         imageViewinsta.setOnClickListener {
             openinsta()
         }
        imageViewlinkdin.setOnClickListener {
            oopenlinkdin()
        }


    }

    private fun setheadername() {
        var info_pref_obj= intro_prefrence(this)
        dlayout.duo_view_header_text_title.text = info_pref_obj.getname().toString().toUpperCase(Locale.ROOT)
    }

    private fun oopenlinkdin() {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://hannan-shahid-a67798161"))
        val packageManager: PackageManager = getPackageManager()
        val list =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        if (list.isEmpty()) {
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.linkedin.com/profile/view?id=hannan-shahid-a67798161")
            )
        }
        startActivity(intent)
    }

    private fun openinsta() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://instagram.com/_u/hannan.shahid0/")
            intent.setPackage("com.instagram.android")
            startActivity(intent)
        } catch (anfe: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/hannan.shahid0/")
                )
            )
        }
    }

    private fun openwhatsapp() {
        val url = "https://api.whatsapp.com/send?phone=$+923341617255"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun openfacebook() {
        val facebookUrl = "www.facebook.com/hannan.shahid.73/"
        val facebookID = "2816510415142341"

        try {
            val versionCode: Int = getApplicationContext().getPackageManager()
                .getPackageInfo("com.facebook.katana", 0).versionCode
            if (!facebookID.isEmpty()) {
                // open the Facebook app using facebookID (fb://profile/facebookID or fb://page/facebookID)
                val uri = Uri.parse("fb://page/$facebookID")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else if (versionCode >= 3002850 && !facebookUrl.isEmpty()) {
                // open Facebook app using facebook url
                val uri =
                    Uri.parse("fb://facewebmodal/f?href=$facebookUrl")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            } else {
                // Facebook is not installed. Open the browser
                val uri = Uri.parse(facebookUrl)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            // Facebook is not installed. Open the browser
            val uri = Uri.parse(facebookUrl)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

    }

    override fun onBackPressed() {

            val transaction =
                supportFragmentManager.beginTransaction().hide(currentfrag).show(homefrag).commit()
            currentfrag = homefrag
            textViewtoolbar.setText("Thought's")
            duomenuadpater.setViewSelected(0, true)
            setheadername()
            dlayout.closeDrawer()


    }

    private fun handleMenu()
    {
        duomenuadpater = Menuadapter(mTitles)
        duomenu.setOnMenuClickListener(this)
        duomenu.adapter = duomenuadpater
    }
    private fun goToFragment(fragment: Fragment) {

        val transaction =
            supportFragmentManager.beginTransaction().hide(currentfrag).show(fragment).commit()
        currentfrag=fragment

    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {
        duomenuadpater.setViewSelected(position, true);
        if(position==0) {
            textViewtoolbar.setText("Thought's")
        }
        else
        {
            textViewtoolbar.setText(mTitles[position])
        }
        // Navigate to the right fragment
        when (position) {
            0-> {
                goToFragment(homefrag);

            }
            1->
            {
                goToFragment(adnotefrag);
            }
            2->
            {
                goToFragment(settingfrag);
            }
            3->
            {
                goToFragment(aboutusfrag)
            }

        }

        // Close the drawer
       dlayout.closeDrawer()
    }

    override fun onHeaderClicked()
    {

    }

    override fun onFooterClicked() {

    }

}