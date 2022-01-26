package com.bhimraj.altimetrikassessment.repositories

import com.bhimraj.altimetrikassessment.interfaces.NetworkResponseCallback
import androidx.lifecycle.MutableLiveData
import com.bhimraj.altimetrikassessment.models.LaunchesItem
import com.bhimraj.altimetrikassessment.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaunchesRepository private constructor() {
    private lateinit var mCallBack: NetworkResponseCallback
    private var mLaunchList: MutableLiveData<List<LaunchesItem>> = MutableLiveData<List<LaunchesItem>>().apply { value = emptyList() }

    companion object {
        private var mInstance: LaunchesRepository? = null

        fun getInstance(): LaunchesRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = LaunchesRepository()
                }
            }
            return mInstance!!
        }
    }

    private lateinit var mLaunchCall: Call<List<LaunchesItem>>

    fun getLaunches(
        callback: NetworkResponseCallback,
        forceFetch: Boolean
    ): MutableLiveData<List<LaunchesItem>> {
        mCallBack = callback
        if (mLaunchList.value!!.isEmpty() && !forceFetch) {
            mCallBack.onNetworkSuccess()
            return mLaunchList
        }
        mLaunchCall = RestClient.getInstance().getApiService().getLaunches()
        mLaunchCall.enqueue(object : Callback<List<LaunchesItem>> {
            override fun onResponse(call: Call<List<LaunchesItem>>, response: Response<List<LaunchesItem>>) {
                mLaunchList.value=response.body()
                mCallBack.onNetworkSuccess()
            }

            override fun onFailure(call: Call<List<LaunchesItem>>, t: Throwable) {
                mLaunchList.value = emptyList()
                mCallBack.onNetworkFailure(t)
            }

        })
        return mLaunchList
    }
}