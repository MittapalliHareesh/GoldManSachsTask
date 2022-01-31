package com.goldmanscachs.task.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goldmanscachs.task.view.AstronomyPictureFragment
import com.goldmanscachs.task.view.FavoriteFragment

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AstronomyPictureFragment.getInstance()
            1 -> FavoriteFragment.getInstance()
            else -> throw IllegalStateException("No fragment defined for position: $position")
        }
    }
}