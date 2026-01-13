package com.example.mocktest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WizardAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 6   // ✅ MUST be 6 pages

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LanguageFragment()
            1 -> AddImageFragment.newInstance(1)
            2 -> AddImageFragment.newInstance(2)
            3 -> AddImageFragment.newInstance(3)   // ✅ Add Image 3
            4 -> ObservationFragment()             // ✅ NEXT after Add Image 3
            else -> ShowcaseFragment()
        }
    }
}
