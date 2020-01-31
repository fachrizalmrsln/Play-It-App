package com.id.zul.playit.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.id.zul.playit.R
import com.id.zul.playit.adapter.movie.MovieItemAdapter
import com.id.zul.playit.ui.detail.DetailActivity
import com.id.zul.playit.utils.EndlessScrollListener
import com.id.zul.playit.viewmodel.factory.ViewModelFactory
import com.id.zul.playit.viewmodel.ui.movie.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class MovieFragment : Fragment() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapterMovie: MovieItemAdapter
    private lateinit var shimmer: SkeletonScreen

    private var page = 1
    private var offset = 0

    companion object {
        fun getInstance(): Fragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            initializeToolbar()
            viewModel = initializeViewModel(requireActivity())
            setRecyclerView(view)

            setShimmer()

            getMovie(page)
        }

    }

    private fun initializeToolbar() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        toolbar.title = resources.getString(R.string.movie_toolbar_title)
    }

    private fun initializeViewModel(activity: FragmentActivity): MovieViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
    }

    private fun setShimmer() {
        shimmer = Skeleton.bind(rv_movies)
            .adapter(adapterMovie)
            .load(R.layout.skeleton_item_list)
            .color(R.color.shimmerColor)
            .angle(45)
            .frozen(false)
            .duration(1000)
            .show()
    }

    private fun getMovie(page: Int) {
        viewModel.getNowPlaying(page).observe(
            this,
            Observer {
                if (it.isNullOrEmpty())
                    toast("Check your internet connection")
                else {
                    if (offset == 0) {
                        shimmer.show()
                        adapterMovie.onReplace(it)
                        shimmer.hide()
                    } else {
                        adapterMovie.onUpdate(it)
                    }
                }
            }
        )
    }

    private fun setRecyclerView(view: View) {
        adapterMovie = MovieItemAdapter(view.context) {
            startActivity<DetailActivity>(
                "identify" to "movie",
                "data_id" to it.id
            )
        }

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE)
            rv_movies.layoutManager = GridLayoutManager(context, 4)
        else
            rv_movies.layoutManager = GridLayoutManager(context, 2)

        rv_movies.adapter = adapterMovie
        rv_movies.clearOnScrollListeners()
        rv_movies.addOnScrollListener(EndlessScrollListener {
            offset += 20
            page += 1
            getMovie(page)
        })
    }

}