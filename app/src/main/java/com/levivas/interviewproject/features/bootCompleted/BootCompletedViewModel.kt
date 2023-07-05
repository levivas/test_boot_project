package com.levivas.interviewproject.features.bootCompleted

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BootCompletedViewModel @Inject constructor(private val repository: BootCompletedRepository) : ViewModel() {

    fun getBootCompletedTimestamps() = repository.getBootCompletedTimestamps()
}