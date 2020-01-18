package com.id.zul.playit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.id.zul.playit.R
import com.id.zul.playit.ui.favorite.FavoriteFragment
import com.id.zul.playit.ui.movie.MovieFragment
import com.id.zul.playit.ui.tv.TvFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBottomNav()
    }

    private fun setBottomNav() {
        bottom_nav_main.setOnNavigationItemSelectedListener { items ->
            when (items.itemId) {
                R.id.nav_movie -> openFragment(MovieFragment.getInstance())
                R.id.nav_tv -> openFragment(TvFragment.getInstance())
                R.id.nav_favorite -> openFragment(FavoriteFragment.getInstance())
            }
            true
        }
        bottom_nav_main.selectedItemId = R.id.nav_movie
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .apply {
                replace(R.id.container_view_main, fragment)
                commit()
            }
    }
}
