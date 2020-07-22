package com.example.kotlinuberriderremake

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController
    private lateinit var imgAvatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun init() {
        navView.setNavigationItemSelectedListener { item ->
            if (item.itemId == R.id.nav_sign_out) {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle("Sign out")
                    .setMessage("Do you want to sign out ?")
                    .setCancelable(false)
                    .setNegativeButton("Cancel") { dialogInterface, p1 -> dialogInterface.dismiss() }
                    .setPositiveButton("Sign out") { dialogInterface, p1 ->
                        FirebaseAuth.getInstance().signOut()
                        val intent = Intent(this, SplashScreenActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    }
                val dialog: AlertDialog = builder.create()
                dialog.setOnShowListener { dialogInterface ->
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                }
                dialog.show()
            }
            true
        }

        // Set data for user
        val headerView = navView.getHeaderView(0)
        val txtName = headerView.findViewById(R.id.txt_name) as TextView
        val txtPhone = headerView.findViewById(R.id.txt_phone) as TextView
        imgAvatar = headerView.findViewById(R.id.img_avatar) as ImageView

        txtName.text = Common.buildWelcomeMessage()
        txtPhone.text = Common.currentRider?.phoneNumber ?: ""
    }
}