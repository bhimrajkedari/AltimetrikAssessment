package com.bhimraj.altimetrikassessment.viewmodels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bhimraj.altimetrikassessment.interfaces.NetworkResponseCallback
import com.bhimraj.altimetrikassessment.models.LaunchesItem
import com.bhimraj.altimetrikassessment.repositories.LaunchesRepository
import com.bhimraj.altimetrikassessment.utils.NetworkHelper

class LaunchesListViewModel(private val app: Application) : AndroidViewModel(app) {
    private var mLaunchList: MutableLiveData<List<LaunchesItem>> = MutableLiveData<List<LaunchesItem>>().apply { value = emptyList() }
    val mShowProgressBar = MutableLiveData(true)
    val mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val mShowApiError = MutableLiveData<String>()
    private var mLaunchRepository = LaunchesRepository.getInstance()

    fun fetchLaunchesFromServer(forceFetch: Boolean): MutableLiveData<List<LaunchesItem>> {
        if (NetworkHelper.isOnline(app.baseContext)) {
            mShowProgressBar.value = true
            mLaunchList = mLaunchRepository.getLaunches(object : NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = th.message
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mLaunchList
    }

    fun onRefreshClicked(view: View) {
        fetchLaunchesFromServer(true)
    }
}