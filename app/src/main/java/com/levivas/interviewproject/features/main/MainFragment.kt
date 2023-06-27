package com.levivas.interviewproject.features.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.levivas.interviewproject.R
import com.levivas.interviewproject.extensions.viewBinding
import com.levivas.interviewproject.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyInsets()
        observeChanges()
    }

    private fun observeChanges() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getItems().collectLatest {
                    binding.tvBootCounter.text = when(it.size) {
                        0 -> "No boots detected"
                        else -> {
                            var uiInfo = ""
                            it.forEach {item -> uiInfo = " ${item.id} - ${item.timestamp}"  }
                            uiInfo
                        }
                    }
                }
            }
        }
    }

    private fun applyInsets() {
        binding.llRoot.applyInsetter { type(statusBars = true) { padding(top = true) } }
    }
}