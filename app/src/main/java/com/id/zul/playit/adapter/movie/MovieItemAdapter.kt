package com.id.zul.playit.adapter.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.id.zul.playit.R
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.utils.ConvertDate
import com.id.zul.playit.utils.Network
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class MovieItemAdapter(
    private val context: Context,
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {

    private var movie: MutableList<Movie> = mutableListOf()

    fun onReplace(data: List<Movie>) {
        movie.clear()
        movie.addAll(data)
        notifyDataSetChanged()
    }

    fun onUpdate(data: List<Movie>){
        movie.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_item_list, parent, false)
        )
    }

    override fun getItemCount() = movie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movie[position].let { holder.bindItem(it, listener) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.find(R.id.tv_item_title)
        private val tvDate: TextView = itemView.find(R.id.tv_item_date)
        private val ivPoster: ImageView = itemView.find(R.id.iv_item_image)

        fun bindItem(data: Movie, listener: (Movie) -> Unit) {
            val poster = Network.POSTER_PATH + data.poster_path
            val date = ConvertDate.dateToOnlyYear(data.release_date)

            tvTitle.text = data.title
            tvDate.text = date
            itemView.context.let {
                Picasso
                    .get()
                    .load(poster)
                    .placeholder(R.drawable.place_holder)
                    .into(ivPoster)
            }
            itemView.setOnClickListener { listener(data) }
        }
    }
}
