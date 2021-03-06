package com.whoadityanawandar.ecommerce

import com.whoadityanawandar.ecommerce.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide

import com.whoadityanawandar.ecommerce.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        binding.appBarHome.fabCart.setOnClickListener { view ->
            var intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_categories,
                R.id.nav_orders,
                R.id.nav_settings,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //fetch user name and image and display in nav header
        var sharedPreferences = getSharedPreferences("name", AppCompatActivity.MODE_PRIVATE)
        var name = sharedPreferences.getString("name", "")
        var profilePicUrl = sharedPreferences.getString("profilePicUrl", "")

/*        val navigationView = root.findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)*/

        //set user's name and profile image in the side-panel navbar
        var headerView = navView.getHeaderView(0)
        var txtvwUsername = headerView.findViewById<TextView>(R.id.txtvwUsername)
        txtvwUsername.text = name
        var imgvwProfilePic = headerView.findViewById<ImageView>(R.id.imgvwProfilePic)
        imgvwProfilePic.setImageURI(Uri.parse(profilePicUrl))
        Glide.with(applicationContext).load(profilePicUrl).fitCenter()
            .placeholder(R.drawable.profile).into(imgvwProfilePic);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}