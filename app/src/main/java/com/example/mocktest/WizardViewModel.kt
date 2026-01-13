package com.example.mocktest

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WizardViewModel : ViewModel() {
    val selectedLanguage = MutableLiveData("English")
    val image1 = MutableLiveData<Uri?>(null)
    val image2 = MutableLiveData<Uri?>(null)
    val image3 = MutableLiveData<Uri?>(null)
}
