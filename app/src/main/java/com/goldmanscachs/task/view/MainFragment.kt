package com.goldmanscachs.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.goldmanscachs.task.R
import com.goldmanscachs.task.adapter.SectionsPagerAdapter
import com.goldmanscachs.task.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mainFragment: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mainFragment = FragmentMainBinding.inflate(inflater, container, false)
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        mainFragment.cmlContainer.adapter = sectionsPagerAdapter
        mainFragment.cmlContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

        })
        return mainFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TabLayoutMediator(mainFragment.cmlTabs, mainFragment.cmlContainer) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.cml_tab_title_Astronomy_picture)
                1 -> tab.text = getString(R.string.cml_tab_title_favorite)
            }
        }.attach()
    }
}