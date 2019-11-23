package demo.nitin.tumblr_android_demo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Priority
import demo.nitin.tumblr_android_demo.di.GlideApp

object ImageLoadingUtil {

    /**
     * sets the image from the url into the android image view
     *
     * @param context - android context to display the image
     * @param imageUrl - url to load the images
     * @param targetView - android image view where the image has to be displayed
     */
    fun loadImage(context: Context, imageUrl: String?, targetView: ImageView?) {
        targetView?.let {
            GlideApp.with(context)
                .asBitmap()
                .optionalFitCenter()
                .priority(Priority.HIGH)
                .load(imageUrl)
                .into(targetView)
        }
    }
}
