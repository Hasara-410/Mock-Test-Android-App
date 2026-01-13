package com.example.mocktest

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private val vm by activityViewModels<WizardViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val act = requireActivity() as WizardActivity

        view.findViewById<Button>(R.id.btnSinhala).setOnClickListener {
            vm.selectedLanguage.value = "සිංහල"
            act.next()
        }
        view.findViewById<Button>(R.id.btnTamil).setOnClickListener {
            vm.selectedLanguage.value = "தமிழ்"
            act.next()
        }
        view.findViewById<Button>(R.id.btnEnglish).setOnClickListener {
            vm.selectedLanguage.value = "English"
            act.next()
        }
    }
}

