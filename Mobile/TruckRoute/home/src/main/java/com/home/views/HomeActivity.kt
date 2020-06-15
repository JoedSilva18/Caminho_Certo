package com.home.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.core.base.BaseActivity
import com.core.customviews.CurvedBottomNavigation
import com.home.R
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.architecture.ext.viewModel
import java.net.URLEncoder


class HomeActivity : BaseActivity<HomeViewModel>() {
    override val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val curvedBottomNavigation: CurvedBottomNavigation = findViewById(R.id.bottomNavigationHome)
        curvedBottomNavigation.inflateMenu(R.menu.item_menu_bottom_navigation)
        curvedBottomNavigation.selectedItemId = R.id.action_home

        setNavigationController(curvedBottomNavigation)

        viewHomeWhats.setOnClickListener { onClickWhatsApp() }
    }

    private fun setNavigationController(curvedBottomNavigation: CurvedBottomNavigation) {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        curvedBottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.action_user -> imgHome.background = getDrawable(R.drawable.ic_search_grey)
                R.id.action_home -> imgHome.background = getDrawable(R.drawable.ic_search_orange)
                R.id.action_message -> {
                    imgHome.background = getDrawable(R.drawable.ic_search_grey)
                    onClickWhatsApp()
                }
            }
        }
    }

    private fun onClickWhatsApp() {
        try {
            val sendMsg = Intent(Intent.ACTION_VIEW)
            val url =
                "https://api.whatsapp.com/send?phone=" + "+1(415)5238886" + "&text=" + URLEncoder.encode(
                    "Olá, tudo bem ?",
                    "UTF-8"
                )
            sendMsg.setPackage("com.whatsapp")
            sendMsg.data = Uri.parse(url)
            if (sendMsg.resolveActivity(packageManager) != null) {
                startActivity(sendMsg)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}