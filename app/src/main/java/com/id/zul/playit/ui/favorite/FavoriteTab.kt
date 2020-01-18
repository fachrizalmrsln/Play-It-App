package com.id.zul.playit.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.id.zul.playit.ui.favorite.movie.FavoriteMovieFragment
import com.id.zul.playit.ui.favorite.tv.FavoriteTvFragment

class FavoriteTab(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> FavoriteMovieFragment.getInstance()
        1 -> FavoriteTvFragment.getInstance()
        else -> FavoriteFragment.getInstance()
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Movie"
        1 -> "Tv Show"
        else -> "Favorite"
    }

}