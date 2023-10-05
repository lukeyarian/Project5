package com.example.project5
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import com.example.project5.databinding.TranslateFragmentBinding
import com.example.project5.databinding.ActivityMainBinding

class TranslateFragment : Fragment() {
    private val translationViewModel: TranslationViewModel by activityViewModels()
    private var _binding: TranslateFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TranslateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val (inputLanguage, outputLanguage) = (activity as MainActivity).getSelectedLanguages()
                translationViewModel.translate(s.toString(), inputLanguage, outputLanguage)
            }
        })
        translationViewModel.translatedText.observe(viewLifecycleOwner, Observer { translatedText ->
            val mainBinding = ActivityMainBinding.bind(view.rootView)
            mainBinding.translatedText.text = translatedText
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}