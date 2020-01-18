package com.id.zul.playit.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.google.android.material.snackbar.Snackbar
import com.id.zul.playit.R
import com.id.zul.playit.model.favorite.FavoriteEntity
import com.id.zul.playit.model.movie.Movie
import com.id.zul.playit.model.tv.Tv
import com.id.zul.playit.utils.ConvertDate
import com.id.zul.playit.utils.ConvertDecimal
import com.id.zul.playit.utils.Network
import com.id.zul.playit.viewmodel.factory.ViewModelFactory
import com.id.zul.playit.viewmodel.ui.detail.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var ivBackDrop: ImageView
    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvRate: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rbDetail: RatingBar
    private lateinit var shimmer: SkeletonScreen
    private lateinit var buttonFavorite: Button

    private var dataId: Int = 0
    private var id: Int? = 0
    private var dataType = "default"
    private var dataBackdrop = "default"
    private var dataPoster = "default"
    private var dataTitle = "default"
    private var dataDate = "default"
    private var dataRate = "default"
    private var dataDescription = "default"
    private var dataAge = "default"

    private var favoriteState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initializeToolbar()
        initializeViews()
        getData()

        buttonFavorite()
    }

    private fun initializeViews() {
        ivBackDrop = find(R.id.iv_back_drop_detail)
        ivPoster = find(R.id.iv_poster_detail)
        tvTitle = find(R.id.tv_title_detail)
        tvDate = find(R.id.tv_date_detail)
        tvRate = find(R.id.tv_rate_detail)
        tvAge = find(R.id.tv_age_detail)
        tvDescription = find(R.id.tv_description_detail)
        rbDetail = find(R.id.rb_detail)
        buttonFavorite = find(R.id.btn_favorite)

        viewModel = initializeViewModel(this)
    }

    private fun initializeToolbar() {
        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    private fun initializeViewModel(activity: DetailActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    private fun getData() {
        dataId = intent.getIntExtra("data_id", 0)
        dataType = intent?.getStringExtra("identify").toString()

        getFavorite(dataId)

        if (!favoriteState) {
            if (dataType == "movie")
                getDetailMovie(dataId)
            else
                getDetailTv(dataId)
        }
    }

    private fun getFavorite(id: Int) {
        viewModel.getFavoriteById(id).observe(
            this,
            Observer {
                favoriteState = if (it == null) {
                    stateNotFavorite()
                    false
                } else {
                    stateIsFavorite()
                    setFavorite(it)
                    true
                }
            }
        )
    }

    private fun setShimmer() {
        shimmer = Skeleton.bind(content_detail)
            .load(R.layout.skeleton_detail)
            .color(R.color.shimmerColor)
            .angle(45)
            .duration(2000)
            .show()
    }

    private fun getDetailMovie(id: Int) {
        setShimmer()
        viewModel.getMovieById(id).observe(
            this,
            Observer {
                if (it == null)
                    toast("Check your internet connection")
                else {
                    setDataMovie(it)
                    shimmer.hide()
                }
            }
        )
    }

    private fun getDetailTv(id: Int) {
        setShimmer()
        viewModel.getTvById(id).observe(
            this,
            Observer {
                if (it == null)
                    toast("Check your internet connection")
                else {
                    setDataTv(it)
                    shimmer.hide()
                }
            }
        )
    }

    private fun setDataMovie(data: Movie) {
        dataBackdrop = Network.BACKDROP_PATH + data.backdrop_path
        dataPoster = Network.POSTER_PATH + data.poster_path
        dataTitle = data.title
        dataDate = ConvertDate.dateToIDFormat(data.release_date)
        dataRate = ConvertDecimal.rateToOnlyOneAfterComa(data.vote_average / 2)
        dataDescription = data.overview
        dataAge = data.adult.toString()

        initDataToViews()
    }

    private fun setDataTv(data: Tv) {
        dataBackdrop = Network.BACKDROP_PATH + data.backdrop_path
        dataPoster = Network.POSTER_PATH + data.poster_path
        dataTitle = data.name
        dataDate = ConvertDate.dateToIDFormat(data.first_air_date)
        dataRate = ConvertDecimal.rateToOnlyOneAfterComa(data.vote_average / 2)
        dataDescription = data.overview
        dataAge = "false"

        initDataToViews()
    }

    private fun setFavorite(favoriteEntity: FavoriteEntity) {
        id = favoriteEntity.id
        tvTitle.text = favoriteEntity.favoriteTile
        tvDate.text = favoriteEntity.favoriteDate
        tvRate.text = favoriteEntity.favoriteRate
        tvDescription.text = favoriteEntity.favoriteDescription

        rbDetail.rating = favoriteEntity.favoriteRate.toFloat()

        this.let {
            Picasso
                .get()
                .load(favoriteEntity.favoriteBackdrop)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .fit()
                .into(ivBackDrop)

            Picasso
                .get()
                .load(favoriteEntity.favoritePoster)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .fit()
                .into(ivPoster)
        }

        when (favoriteEntity.favoriteAge) {
            "default" -> tvAge.text = resources.getString(R.string._13)
            "false" -> tvAge.text = resources.getString(R.string._13)
            else -> tvAge.text = resources.getString(R.string._18)
        }

        when (favoriteEntity.favoriteType) {
            "movie" -> toolbar.title = resources.getString(R.string.movie_detail)
            else -> toolbar.title = resources.getString(R.string.tv_detail)
        }

    }

    private fun initDataToViews() {
        tvTitle.text = dataTitle
        tvDate.text = dataDate
        tvRate.text = dataRate
        tvDescription.text = dataDescription

        rbDetail.rating = dataRate.toFloat()

        this.let {
            Picasso
                .get()
                .load(dataBackdrop)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .fit()
                .into(ivBackDrop)

            Picasso
                .get()
                .load(dataPoster)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .fit()
                .into(ivPoster)
        }

        when (dataAge) {
            "default" -> tvAge.text = resources.getString(R.string._13)
            "false" -> tvAge.text = resources.getString(R.string._13)
            else -> tvAge.text = resources.getString(R.string._18)
        }

        when (dataType) {
            "movie" -> toolbar.title = resources.getString(R.string.movie_detail)
            else -> toolbar.title = resources.getString(R.string.tv_detail)
        }

    }

    private fun buttonFavorite() {
        buttonFavorite.setOnClickListener {
            favoriteState = if (favoriteState) {
                stateNotFavorite()
                deleteFavorite()
                false
            } else {
                stateIsFavorite()
                insertFavorite()
                true
            }
        }
    }

    private fun insertFavorite() {
        viewModel.insertFavorite(favoriteEntity())
        Snackbar.make(
            view, "Saved to favorite",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun deleteFavorite() {
        viewModel.deleteFavorite(favoriteEntityForDelete())
        Snackbar.make(
            view, "Deleted from favorite",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun stateNotFavorite() {
        buttonFavorite.background =
            ContextCompat.getDrawable(this, R.drawable.favorite_background)
        buttonFavorite.textColor =
            ContextCompat.getColor(this, R.color.white)
        buttonFavorite.text = getString(R.string.add_to_favorite)

    }

    private fun stateIsFavorite() {
        buttonFavorite.background =
            ContextCompat.getDrawable(this, R.drawable.unfavorite_background)
        buttonFavorite.textColor =
            ContextCompat.getColor(this, R.color.colorPrimary)
        buttonFavorite.text = getString(R.string.remove_from_favorite)

    }

    private fun favoriteEntity(): FavoriteEntity {
        return FavoriteEntity(
            favoriteId = dataId,
            favoriteType = dataType,
            favoriteTile = dataTitle,
            favoriteRate = dataRate,
            favoriteDate = dataDate,
            favoriteAge = dataAge,
            favoritePoster = dataPoster,
            favoriteBackdrop = dataBackdrop,
            favoriteDescription = dataDescription
        )
    }

    private fun favoriteEntityForDelete(): FavoriteEntity {
        return FavoriteEntity(
            id = id,
            favoriteId = dataId,
            favoriteType = dataType,
            favoriteTile = dataTitle,
            favoriteRate = dataRate,
            favoriteDate = dataDate,
            favoriteAge = dataAge,
            favoritePoster = dataPoster,
            favoriteBackdrop = dataBackdrop,
            favoriteDescription = dataDescription
        )
    }

}
