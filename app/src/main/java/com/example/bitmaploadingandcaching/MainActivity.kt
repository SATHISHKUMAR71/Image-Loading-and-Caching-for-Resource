package com.example.bitmaploadingandcaching


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.collection.LruCache
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout

class MainActivity : AppCompatActivity() {

    private var drawableCache = LruCache<Int, Drawable>((Runtime.getRuntime().maxMemory()/4).toInt())
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        val imagesList:MutableList<Drawable> = mutableListOf()
        for (i in 1..10){
            imagesList.add(loadDrawable(R.drawable.ai_img))
            imagesList.add(loadDrawable(R.drawable.bird_img))
            imagesList.add(loadDrawable(R.drawable.bot_balloon))
            imagesList.add(loadDrawable(R.drawable.cat_img))
            imagesList.add(loadDrawable(R.drawable.ice_cream))
            imagesList.add(loadDrawable(R.drawable.ice_cream_2))
            imagesList.add(loadDrawable(R.drawable.img_forest))
            imagesList.add(loadDrawable(R.drawable.img_snow))
            imagesList.add(loadDrawable(R.drawable.infinity))
            imagesList.add(loadDrawable(R.drawable.sad_bot))
            imagesList.add(loadDrawable(R.drawable.tree_img))
            imagesList.add(loadDrawable(R.drawable.website))
            imagesList.add(loadDrawable(R.drawable.wonder_world))
            imagesList.add(loadDrawable(R.drawable.dog_img))
            imagesList.add(loadDrawable(R.drawable.dummy))
            imagesList.add(loadDrawable(R.drawable.fantasy_island))
            imagesList.add(loadDrawable(R.drawable.ice_cream))
            imagesList.add(loadDrawable(R.drawable.ice_cream_2))
            imagesList.add(loadDrawable(R.drawable.img_forest))
            imagesList.add(loadDrawable(R.drawable.img_snow))
            imagesList.add(loadDrawable(R.drawable.infinity))
            imagesList.add(loadDrawable(R.drawable.sad_bot))
            imagesList.add(loadDrawable(R.drawable.tree_img))
            imagesList.add(loadDrawable(R.drawable.website))
            imagesList.add(loadDrawable(R.drawable.wonder_world))
            imagesList.add(loadDrawable(R.drawable.woof))
        }
        rv.adapter = ImagesAdapter(imagesList,baseContext)
        rv.layoutManager = LinearLayoutManager(this)
    }

    private fun loadDrawable(id:Int):Drawable{
        val image = drawableCache.get(id)
        if(image==null){
            val newImage = ContextCompat.getDrawable(baseContext,id)!!
            drawableCache.put(id,newImage)
            println("New Image Loaded ${count++}")
            return newImage
        }
        return image
    }
}