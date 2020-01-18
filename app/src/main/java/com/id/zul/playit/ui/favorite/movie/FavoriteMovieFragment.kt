package com.id.zul.playit.ui.favorite.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.id.zul.playit.R
import com.id.zul.playit.adapter.favorite.FavoriteAdapter
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.ui.detail.DetailActivity
import com.id.zul.playit.viewmodel.factory.ViewModelFactory
import com.id.zul.playit.viewmodel.ui.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var shimmer: SkeletonScreen
    private lateinit var adapter: FavoriteAdapter

    companion object {
        fun getInstance(): Fragment {
            return FavoriteMovieFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            viewModel = initializeViewModel(requireActivity())
            setRecyclerView(view)

            getMovie()
        }

    }

    private fun initializeViewModel(activity: FragmentActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(FavoriteViewModel::class.java)
    }


    private fun getMovie() {
        setShimmer()
        viewModel.getFavoriteMovie().observe(
            this,
            Observer {
                if (it.isNullOrEmpty())
                    emptyViews()
                else {
                    setViews()
                    setDataToViews(it)
                }
                shimmer.hide()
            }
        )
    }

    private fun emptyViews() {
        view_empty_movie.visibility = View.VISIBLE
        rv_favorite_movie.visibility = View.GONE
    }

    private fun setViews() {
        view_empty_movie.visibility = View.GONE
        rv_favorite_movie.visibility = View.VISIBLE
    }

    private fun setShimmer() {
        shimmer = Skeleton.bind(rv_favorite_movie)
            .adapter(adapter)
            .load(R.layout.skeleton_item_list)
            .color(R.color.shimmerColor)
            .angle(45)
            .frozen(false)
            .duration(1000)
            .show()
    }

    private fun setRecyclerView(view: View) {
        adapter = FavoriteAdapter(view.context) {
            startActivity<DetailActivity>(
                "identify" to "movie",
                "data_id" to it.favoriteId
            )
        }

        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            rv_favorite_movie.layoutManager = GridLayoutManager(context, 4)
        else
            rv_favorite_movie.layoutManager = GridLayoutManager(context, 2)

        rv_favorite_movie.adapter = adapter
    }

    private fun setDataToViews(data: List<FavoriteEntity>) {
        val listData: MutableList<FavoriteEntity> = mutableListOf()
        listData.clear()
        listData.addAll(data)
        adapter.setData(listData)
    }

}