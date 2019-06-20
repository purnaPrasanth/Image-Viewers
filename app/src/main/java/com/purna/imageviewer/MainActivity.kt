package com.purna.imageviewer

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.purna.httpclient.HttpClient
import com.purna.imageloadercore.ImageDataLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val httpClient = HttpClient(Dispatchers.IO)
        val imageLoader = ImageDataLoader(Dispatchers.IO, httpClient)

        GlobalScope.launch {
            try {
                val bitmap =
                    BitmapFactory.decodeStream(
                        imageLoader.loadImageData(
                            URL("https://images.unsplash.com/photo-1558981420-bf351ce8e3ca?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjIzNTU2fQ")
                        )
                    )
                launch(Dispatchers.Main) { findViewById<ImageView>(R.id.main_image).setImageBitmap(bitmap) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
