package com.id.zul.playit.adapter.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.id.zul.playit.R
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.utils.ConvertDate
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class FavoriteAdapter(
    private val context: Context,
    private val listener: (FavoriteEntity) -> Unit
) : PagedListAdapter<FavoriteEntity, FavoriteAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var movie: MutableList<FavoriteEntity> = mutableListOf()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity) =
                oldItem.id == newItem.id
        }
    }

    fun setData(data: List<FavoriteEntity>) {
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
        private val ivPoster: ImageView = itemView.find(R.id.iv_item_image)
        private val tvTitle: TextView = itemView.find(R.id.tv_item_title)
        private val tvDate: TextView = itemView.find(R.id.tv_item_date)

        fun bindItem(data: FavoriteEntity, listener: (FavoriteEntity) -> Unit) {
            tvTitle.text = data.favoriteTile
            tvDate.text = ConvertDate.dateFromFavorite(data.favoriteDate)
            itemView.context.let {
                Picasso
                    .get()
                    .load(data.favoritePoster)
                    .placeholder(R.drawable.place_holder)
                    .into(ivPoster)

            }
            itemView.setOnClickListener { listener(data) }
        }
    }

}