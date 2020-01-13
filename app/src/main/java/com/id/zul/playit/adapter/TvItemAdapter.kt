package com.id.zul.playit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.id.zul.playit.R
import com.id.zul.playit.model.tv.show.Tv
import com.id.zul.playit.utils.ConvertDate
import com.id.zul.playit.utils.Network
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TvItemAdapter(
    private val context: Context,
    private val listener: (Tv) -> Unit
) : RecyclerView.Adapter<TvItemAdapter.ViewHolder>() {

    private var movie: MutableList<Tv> = mutableListOf()

    fun setData(data: List<Tv>) {
        movie.clear()
        movie.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_item_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movie[position].let { holder.bindItem(it, listener) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.find(R.id.tv_item_title)
        private val tvDate: TextView = itemView.find(R.id.tv_item_date)
        private val ivPoster: ImageView = itemView.find(R.id.iv_item_image)

        fun bindItem(data: Tv, listener: (Tv) -> Unit) {
            val poster = Network.POSTER_PATH + data.poster_path
            val date = ConvertDate.dateToOnlyYear(data.first_air_date)

            tvTitle.text = data.name
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
