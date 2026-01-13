package com.example.mocktest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class AddImageFragment : Fragment(R.layout.fragment_add_image) {

    private val vm by activityViewModels<WizardViewModel>()
    private var index: Int = 1

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            when (index) {
                1 -> vm.image1.value = uri
                2 -> vm.image2.value = uri
                3 -> vm.image3.value = uri
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        index = requireArguments().getInt("index", 1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = requireActivity() as WizardActivity

        val title = view.findViewById<TextView>(R.id.title)
        val imgPreview = view.findViewById<ImageView>(R.id.imgPreview)
        val txtHint = view.findViewById<TextView>(R.id.txtHint)

        title.text = "Add Image $index"

        // ---------- SAFE LANGUAGE DROPDOWN ----------
        val langBox = view.findViewById<View?>(R.id.langBox)
        val txtLang = view.findViewById<TextView?>(R.id.txtLang)
        val btnArrow = view.findViewById<ImageView?>(R.id.btnArrow)

        if (langBox != null && txtLang != null && btnArrow != null) {

            fun showLangMenu() {
                val popup = PopupMenu(requireContext(), btnArrow)
                popup.menu.add("English")
                popup.menu.add("සිංහල")
                popup.menu.add("தமிழ்")

                popup.setOnMenuItemClickListener { item ->
                    vm.selectedLanguage.value = item.title.toString()
                    true
                }
                popup.show()
            }

            langBox.setOnClickListener { showLangMenu() }
            btnArrow.setOnClickListener { showLangMenu() }

            vm.selectedLanguage.observe(viewLifecycleOwner) {
                txtLang.text = it
            }
        }
        // -------------------------------------------

        // Pick image
        view.findViewById<View>(R.id.imageCard).setOnClickListener {
            pickImage.launch("image/*")
        }

        // Buttons
        val btnFullNext = view.findViewById<Button>(R.id.btnAreaFullNext)
        val areaPrevNext = view.findViewById<View>(R.id.btnAreaPrevNext)

        if (index == 1) {
            btnFullNext.visibility = View.VISIBLE
            areaPrevNext.visibility = View.GONE
            btnFullNext.setOnClickListener { act.next() }
        } else {
            btnFullNext.visibility = View.GONE
            areaPrevNext.visibility = View.VISIBLE
            view.findViewById<Button>(R.id.btnPrev).setOnClickListener { act.prev() }
            view.findViewById<Button>(R.id.btnNext).setOnClickListener { act.next() }
        }

        // Show image
        val live = when (index) {
            1 -> vm.image1
            2 -> vm.image2
            else -> vm.image3
        }

        live.observe(viewLifecycleOwner) { uri ->
            if (uri != null) {
                imgPreview.setImageURI(uri)
                txtHint.visibility = View.GONE
            } else {
                txtHint.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance(index: Int) = AddImageFragment().apply {
            arguments = Bundle().apply { putInt("index", index) }
        }
    }
}
