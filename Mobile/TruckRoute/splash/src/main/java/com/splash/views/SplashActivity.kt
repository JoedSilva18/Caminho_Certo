package com.splash.views

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Property
import android.view.View
import android.view.WindowManager
import com.core.base.BaseActivity
import com.core.base.navigateTo
import com.core.extensions.doWait
import com.core.extensions.fadeIn
import com.core.extensions.fadeOut
import com.home.views.HomeActivity
import com.splash.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*
import kotlinx.coroutines.launch
import org.koin.android.architecture.ext.viewModel

class SplashActivity : BaseActivity<SplashViewModel>() {
    override val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initializeComponents()
    }

    private fun initializeComponents() {

        viewModel.launch {
            startAnimationBird()
            doWait(700) {
                motionLayoutSplash.transitionToEnd()
                motionLayoutSplash.transitionToStart()
            }

            if (getValueBoolean()) {
                motionLayoutSplash.btnGoogle.visibility = View.VISIBLE
                motionLayoutSplash.txtWelcomeSplash.visibility = View.GONE
            } else {
                motionLayoutSplash.txtWelcomeSplash.visibility = View.VISIBLE
                motionLayoutSplash.btnGoogle.visibility = View.GONE

                doWait(3000) {
                    viewModel.navigateTo<HomeActivity>(this@SplashActivity)
                    finish()
                }
            }
        }
    }

    private fun startAnimationBird() {
        //Tamanho do display
        val wm = this.getSystemService(WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val displaySize = Point()
        display.getSize(displaySize)

        //Animação
        val fly = getDrawable(R.drawable.animated_transaction) as AnimatedVectorDrawable
        imgFlySplash.setImageDrawable(fly)
        fly.start()

        setAnimator(View.TRANSLATION_X, imgFlySplash.right + 0f, -(displaySize.x + 0f))
        setAnimator(View.TRANSLATION_Y, 0f, -400f)
    }

    private fun setAnimator(
        translation: Property<View, Float>,
        valuesStart: Float,
        valuesEnd: Float
    ) {
        ObjectAnimator.ofFloat<View>(imgFlySplash, translation, valuesStart, valuesEnd)
            .apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.RESTART
                duration = 8000
                start()
            }
    }

    fun onClickGoogle(view: View) {
        save()

        viewModel.navigateTo<HomeActivity>(this@SplashActivity)
        finish()
    }

    private fun save() {
        val sharedPref: SharedPreferences = getSharedPreferences("caminhoCerto", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putBoolean("isFirst", false)

        editor.apply()
    }

    private fun getValueBoolean(): Boolean {
        val sharedPref: SharedPreferences = getSharedPreferences("caminhoCerto", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("isFirst", true)
    }
}