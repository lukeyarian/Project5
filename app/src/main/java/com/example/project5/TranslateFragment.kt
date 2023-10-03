package com.example.project5
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.project5.databinding.TranslateFragmentBinding
class TranslateFragment : Fragment() {
    private var _binding: TranslateFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedViewModel: TranslationViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = TranslateFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextInput.addTextChangedListener { text ->
            sharedViewModel.textToTranslate.value = text?.toString()
        }
    }
}