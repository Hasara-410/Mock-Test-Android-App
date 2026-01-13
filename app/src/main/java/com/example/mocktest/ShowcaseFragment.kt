package com.example.mocktest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ShowcaseFragment : Fragment(R.layout.fragment_showcase) {

    private val vm by activityViewModels<WizardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val act = requireActivity() as WizardActivity

        // dropdown
        val langBox = view.findViewById<View>(R.id.langBox)
        val txtLang = view.findViewById<TextView>(R.id.txtLang)
        val btnArrow = view.findViewById<ImageView>(R.id.btnArrow)

        fun showLangMenu() {
            val popup = PopupMenu(requireContext(), btnArrow)
            popup.menu.add("English")
            popup.menu.add("සිංහල")
            popup.menu.add("தமிழ்")
            popup.setOnMenuItemClickListener {
                vm.selectedLanguage.value = it.title.toString()
                true
            }
            popup.show()
        }

        langBox.setOnClickListener { showLangMenu() }
        btnArrow.setOnClickListener { showLangMenu() }
        vm.selectedLanguage.observe(viewLifecycleOwner) { txtLang.text = it }

        // ✅ pager (3 cards preview)
        val pager = view.findViewById<ViewPager2>(R.id.pagerShow)

        fun refreshPager() {
            val list = listOf(vm.image1.value, vm.image2.value, vm.image3.value)
            pager.adapter = ShowcasePagerAdapter(list)
        }

        // IMPORTANT: allow drag
        pager.isUserInputEnabled = true
        pager.offscreenPageLimit = 3
        pager.clipToPadding = false
        pager.clipChildren = false

        // show side cards (padding)
        pager.setPadding(90, 0, 90, 0)

        // transformer: spacing + scale
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.90f + (r * 0.10f)
            page.scaleX = 0.90f + (r * 0.10f)
            page.alpha = 0.60f + (r * 0.40f)
        }
        pager.setPageTransformer(transformer)

        vm.image1.observe(viewLifecycleOwner) { refreshPager() }
        vm.image2.observe(viewLifecycleOwner) { refreshPager() }
        vm.image3.observe(viewLifecycleOwner) { refreshPager() }
        refreshPager()

        // buttons
        view.findViewById<Button>(R.id.btnPrev).setOnClickListener { act.prev() }
        view.findViewById<Button>(R.id.btnFinish).setOnClickListener { act.goToHome() }
    }
}
