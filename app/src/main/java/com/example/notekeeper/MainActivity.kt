package com.example.notekeeper

import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.Adapter.NoteRecycleAdapter
import com.jwhh.notekeeper.CourseRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.appcompat.app.ActionBarDrawerToggle as AppActionBarDrawerToggle

@RequiresApi(Build.VERSION_CODES.P)
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val noteLayoutManager by lazy {LinearLayoutManager(this)  }

    private val noteRecycleAdapter by lazy { NoteRecycleAdapter(this, DataManager.notes) }

    private val gridLayoutManager by lazy { GridLayoutManager(this, 2) }

    private val courseRecyclerAdapter by lazy {CourseRecyclerAdapter(this, DataManager.courses.values.toList())  }

    private lateinit var mDbOpenHelper:NoteKeeperOpenHelper



    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

       mDbOpenHelper = NoteKeeperOpenHelper(this)
        initFab()
        initialiseDisplayContent()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val toggle = AppActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        toolbar.setCollapseIcon(R.drawable.ic_arrow_back_24)
        navView.setNavigationItemSelectedListener(this)


     //   val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_notes, R.id.nav_courses, R.id.nav_slideshow), drawerLayout)
      //  setupActionBarWithNavController(navController, appBarConfiguration)
      //  navView.setupWithNavController(navController)
    }



    private fun initialiseDisplayContent() {
        DataManager.loadFromDataBase(mDbOpenHelper)
        val listAdapter = noteRecycleAdapter
        val manger = noteLayoutManager

        listItems.layoutManager = manger
        listItems.setHasFixedSize(true)
        listItems.adapter = listAdapter

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.menu.findItem(R.id.nav_notes).isChecked = true

    }

    private fun initFab() {
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, ListActivity::class.java)
            startActivity(activityIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }
    }
    fun animateActivity (){
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_notes ->{
                initialiseDisplayContent()
            }
            R.id.nav_courses ->{
                displayCourses()
            }
        }
        return true
    }

    private fun displayCourses() {
        listItems.layoutManager = gridLayoutManager
        listItems.adapter = courseRecyclerAdapter

        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.menu.findItem(R.id.nav_courses).isChecked = true
    }

    override fun onResume() {
        super.onResume()
        listItems.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        mDbOpenHelper.close()
        super.onDestroy()
    }
}