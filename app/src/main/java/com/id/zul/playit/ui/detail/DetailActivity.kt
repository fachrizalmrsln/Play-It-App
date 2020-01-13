package com.id.zul.playit.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.id.zul.playit.R
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
import org.jetbrains.anko.toast

class DetailActivity : AppCompatActivity() {

    private var dataType: String = "default"
    private lateinit var viewModel: DetailViewModel
    private var dataId: Int = 0

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

    private var dataBackdrop = "default"
    private var dataPoster = "default"
    private var dataTitle = "default"
    private var dataDate = "default"
    private var dataRate = "default"
    private var dataDescription = "default"
    private var dataAge = "default"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initializeToolbar()
        initializeViews()
        getData()
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

        viewModel = initializeViewModel(this)
    }

    private fun initializeToolbar() {
        toolbar = find(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
    }

    private fun initializeViewModel(activity: DetailActivity): DetailViewModel {
        val factory = ViewModelFactory.getInstance()
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    private fun getData() {
        dataType = intent.getStringExtra("identify")!!
        dataId = intent.getIntExtra("data_id", 0)

        if (dataType == "movie") {
            getDetailMovie(dataId)
        } else {
            getDetailTv(dataId)
        }
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
                    toast("Empty")
                else {
                    setDataMovie(it)
                }
                shimmer.hide()
            }
        )
    }

    private fun getDetailTv(id: Int) {
        setShimmer()
        viewModel.getTvById(id).observe(
            this,
            Observer {
                if (it == null)
                    toast("Empty")
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

    @SuppressLint("SetTextI18n")
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
            "default" -> tvAge.text = "13"
            "false" -> tvAge.text = "13"
            else -> tvAge.text = "18"
        }

        when (dataType) {
            "movie" -> toolbar.title = "Detail Movie"
            else -> toolbar.title = "Detail Tv Show"
        }

    }

}
