package com.example.bitmaploadingandcaching

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.collection.LruCache
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import java.net.URL

class ImagesAdapter(private var imageList:MutableList<Drawable>,private var context: Context):RecyclerView.Adapter<ImagesAdapter.ImageHolder>(){
    private var i =0
    private var bitmapCache = LruCache<String,Bitmap>((Runtime.getRuntime().maxMemory()/4).toInt())

    inner class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageView: ImageView = itemView.findViewById<ImageView>(R.id.imageView2)
//        val shimmer:ShimmerFrameLayout = itemView.findViewById(R.id.shimmer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        holder.imageView.setImageDrawable(imageList[position])
    }

    private fun downloadImage(imageUrl:String): Bitmap?{
        if(bitmapCache.get(imageUrl)==null){
            return try{
                val url = URL(imageUrl).openConnection()
                url.connect()
                val inputStream = url.getInputStream()
                val downloadedImage = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                bitmapCache.put(imageUrl,downloadedImage)
                println(bitmapCache.toString())
                println("Image Downloaded Successfully ${i++}")
                println(bitmapCache.snapshot())
//              DiskCache.saveBitMap(context,"image$i",downloadedImage)
                downloadedImage
            }
            catch (e:Exception){
                println("Exception: $e")
                null
            }
        }
        else{
            return bitmapCache.get(imageUrl)
        }
    }
}