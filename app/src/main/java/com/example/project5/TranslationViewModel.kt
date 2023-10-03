package com.example.project5
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class TranslationViewModel : ViewModel() {
    val textToTranslate = MutableLiveData<String>()
    val translatedText = MutableLiveData<String>()
}