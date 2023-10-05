package com.example.project5
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.project5.databinding.ActivityMainBinding
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val translationViewModel: TranslationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, TranslateFragment())
                .commitNow()
        }
        translationViewModel.translatedText.observe(this, Observer { translatedText ->
            binding.translatedText.text = translatedText
        })
    }
    fun getSelectedLanguages(): Pair<String, String> {
        val inputLanguage = when (binding.inputLanguageGroup.checkedRadioButtonId) {
            R.id.englishInput -> "en"
            R.id.spanishInput -> "es"
            R.id.germanInput -> "de"
            else -> "en"
        }
        val outputLanguage = when (binding.outputLanguageGroup.checkedRadioButtonId) {
            R.id.englishOutput -> "en"
            R.id.spanishOutput -> "es"
            R.id.germanOutput -> "de"
            else -> "en"
        }
        return Pair(inputLanguage, outputLanguage)
    }
}