package dev.ajiex.pantaucovid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dev.ajiex.pantaucovid.data.ApiFactory
import dev.ajiex.pantaucovid.data.repository.SummaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel : ViewModel() {
    fun getSummary() = liveData {
        val repo = SummaryRepository(ApiFactory().api)
        withContext(Dispatchers.IO) {
            val res = repo.getSummary()
            if (res.isSuccessful) {
                emit(res.body())
            } else {
                emit(res.errorBody())
            }

        }

    }
}