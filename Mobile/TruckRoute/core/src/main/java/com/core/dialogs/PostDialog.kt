package com.core.dialogs;

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.core.R
import com.core.model.ImageEnum
import com.core.model.Post
import java.util.*

class PostDialog(context: Context, val post: Post) : Dialog(context) {

    private val dialog: Dialog = Dialog(context)

    init {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_post)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        lp.copyFrom(window?.attributes)

        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = lp
    }

    override fun show() {
        val image = dialog.findViewById<ImageView>(R.id.imgDialogPost)
        val stars = dialog.findViewById<RatingBar>(R.id.ratingDialogPost)
        val distance = dialog.findViewById<TextView>(R.id.txtDistanceDialogPost)
        val food = dialog.findViewById<TextView>(R.id.txtFoodDialogPost)
        val shower = dialog.findViewById<TextView>(R.id.txtShowerDialogPost)
        val title = dialog.findViewById<TextView>(R.id.txtTitleDialogPost)
        val close = dialog.findViewById<AppCompatButton>(R.id.btnClose)

        title.text = post.title
        stars.rating = post.stars
        distance.text = post.distance
        food.text = post.food
        shower.text = post.shower

        close.setOnClickListener { dismiss() }

        image.setImageResource(
            when(post.image) {
                ImageEnum.IPIRANGA -> R.drawable.ipiranga_logo
                ImageEnum.BR -> R.drawable.petrobras_logo
                ImageEnum.SHELL -> R.drawable.logo_shell
            }
        )

        try {
            dialog.show()
            Objects.requireNonNull(dialog.window)?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        } catch (e: Exception) {
            //Fix to ViewRootImpl.java crash (BadTokenException)
        }

    }

    override fun dismiss() {
        dialog.dismiss()
    }
}