package com.example.mocktest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ObservationFragment : Fragment(R.layout.fragment_observation) {

    private val vm by activityViewModels<WizardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = requireActivity() as WizardActivity

        // ✅ SAFE nullable
        val langBox = view.findViewById<View?>(R.id.langBox)
        val txtLang = view.findViewById<TextView?>(R.id.txtLang)
        val btnArrow = view.findViewById<ImageView?>(R.id.btnArrow)

        if (langBox != null && txtLang != null && btnArrow != null) {

            vm.selectedLanguage.observe(viewLifecycleOwner) { lang ->
                txtLang.text = lang ?: "English"
            }

            fun showLangMenu() {
                if (!isAdded) return
                val popup = PopupMenu(requireActivity(), btnArrow)
                popup.menu.add("English")
                popup.menu.add("සිංහල")
                popup.menu.add("தமிழ்")
                popup.setOnMenuItemClickListener { item ->
                    vm.selectedLanguage.value = item.title?.toString() ?: "English"
                    true
                }
                popup.show()
            }

            langBox.setOnClickListener { showLangMenu() }
            btnArrow.setOnClickListener { showLangMenu() }
        }

        view.findViewById<Button>(R.id.btnPrev).setOnClickListener { act.prev() }
        view.findViewById<Button>(R.id.btnNext).setOnClickListener { act.next() }
    }
}
