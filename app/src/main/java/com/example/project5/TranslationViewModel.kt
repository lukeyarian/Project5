package com.example.project5
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslationViewModel : ViewModel() {
    val translatedText = MutableLiveData<String>()
    fun translate(text: String, inputLanguage: String, outputLanguage: String) {
        val sourceLang = when (inputLanguage) {
            "en" -> TranslateLanguage.ENGLISH
            "de" -> TranslateLanguage.GERMAN
            "es" -> TranslateLanguage.SPANISH
            else -> return
        }
        val targetLang = when (outputLanguage) {
            "en" -> TranslateLanguage.ENGLISH
            "de" -> TranslateLanguage.GERMAN
            "es" -> TranslateLanguage.SPANISH
            else -> return
        }
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLang)
            .setTargetLanguage(targetLang)
            .build()
        val translator = Translation.getClient(options)
        translator.translate(text)
            .addOnSuccessListener { translatedTextValue ->
                translatedText.value = translatedTextValue
            }
    }
}