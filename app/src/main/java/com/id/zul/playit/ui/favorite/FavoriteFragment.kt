package com.id.zul.playit.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.id.zul.playit.R
import org.jetbrains.anko.find

class FavoriteFragment : Fragment() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    companion object {
        fun getInstance(): Fragment {
            return FavoriteFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (activity != null) {
            initializeToolbar(view)
            initializeViews(view)
            initTabLayout()
        }
    }

    private fun initializeToolbar(view: View) {
        toolbar = view.find(R.id.toolbar_tab)
        toolbar.title = "Favorite"

        tabLayout = view.find(R.id.tl_favorite)
    }

    private fun initializeViews(view: View) {
        viewPager = view.find(R.id.vp_favorite)
    }

    private fun initTabLayout() {
        viewPager.adapter = FavoriteTab(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}