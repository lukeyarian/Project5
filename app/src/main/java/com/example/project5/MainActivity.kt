package com.example.project5
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import android.widget.TextView
import com.google.mlkit.nl.translate.TranslateLanguage
import com.example.project5.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var sharedViewModel: TranslationViewModel
    private lateinit var translator: Translator
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val inputLanguageGroup: RadioGroup = findViewById(R.id.inputLanguageGroup)
        val outputLanguageGroup: RadioGroup = findViewById(R.id.outputLanguageGroup)

        sharedViewModel.textToTranslate.observe(this) { text ->
            val sourceLanguage = getLanguageCode(inputLanguageGroup.checkedRadioButtonId)
            val targetLanguage = getLanguageCode(outputLanguageGroup.checkedRadioButtonId)

            val options = TranslatorOptions.Builder()
                .setSourceLanguage(sourceLanguage)
                .setTargetLanguage(targetLanguage)
                .build()

            translator = Translation.getClient(options)

            translator.translate(text)
                .addOnSuccessListener { translatedText ->
                    sharedViewModel.translatedText.value = translatedText
                }
        }

        sharedViewModel.translatedText.observe(this) {
            binding.translatedText.text = it
        }
    }
    private fun getLanguageCode(radioButtonId: Int): String {
        return when (radioButtonId) {
            R.id.englishInput, R.id.englishOutput -> TranslateLanguage.ENGLISH
            R.id.spanishInput, R.id.spanishOutput -> TranslateLanguage.SPANISH
            R.id.germanInput, R.id.germanOutput -> TranslateLanguage.GERMAN
            R.id.englishInput, R.id.spanishOutput -> TranslateLanguage.SPANISH
            R.id.spanishInput, R.id.englishOutput -> TranslateLanguage.ENGLISH
            R.id.germanInput, R.id.englishOutput -> TranslateLanguage.ENGLISH
            R.id.englishInput, R.id.germanOutput -> TranslateLanguage.GERMAN
            R.id.spanishInput, R.id.germanOutput -> TranslateLanguage.GERMAN
            R.id.germanInput, R.id.spanishOutput -> TranslateLanguage.SPANISH
            else -> TranslateLanguage.ENGLISH
        }
    }
}