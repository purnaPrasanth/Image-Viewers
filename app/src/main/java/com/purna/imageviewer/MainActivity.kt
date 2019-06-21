package com.purna.imageviewer

import androidx.recyclerview.widget.GridLayoutManager
import com.purna.baseandroid.BaseActivity
import com.purna.httpclient.HttpClient
import com.purna.imageviewer.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.net.URL
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val imageAdapter = ImagesRvAdapter(this)

    override fun initUI() {
        val httpClient = HttpClient(ImageViewerApplication.appDispatchersProvider.getInstance().ioDispatcher)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = imageAdapter

        launch(ImageViewerApplication.appDispatchersProvider.getInstance().commonDispatcher) {
            val url =
                URL("https://api.unsplash.com/photos/?client_id=f7af843e895c61a1f3434e6823743a08fb08ace46e203353f539a30eeb2a67e7&per_page=100")

            val jsonObject = JSONArray(Scanner(httpClient.getValues(url)).useDelimiter("\\A").next())

            val listOfUrl = (0 until jsonObject.length()).map { index ->
                jsonObject.getJSONObject(index).getJSONObject("urls").getString("regular")
            }

            launch(ImageViewerApplication.appDispatchersProvider.getInstance().mainDispatcher) {
                imageAdapter.setData(
                    listOfUrl
                )
            }
        }
    }
}
