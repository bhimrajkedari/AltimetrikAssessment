package com.bhimraj.altimetrikassessment.ui

import com.bhimraj.altimetrikassessment.viewmodels.LaunchesListViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhimraj.altimetrikassessment.R
import com.bhimraj.altimetrikassessment.adapters.LaunchesListAdapter
import com.bhimraj.altimetrikassessment.databinding.ActivityLaunchesListBinding

class LaunchesListActivity : AppCompatActivity() {
    private lateinit var mAdapter: LaunchesListAdapter
    private lateinit var mViewModel: LaunchesListViewModel
    private lateinit var mActivityBinding: ActivityLaunchesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_launches_list)

        mViewModel = ViewModelProviders.of(this).get(LaunchesListViewModel::class.java)

        mActivityBinding.viewModel = mViewModel
        mActivityBinding.lifecycleOwner = this

        initializeRecyclerView()
        initializeObservers()
    }

    private fun initializeRecyclerView() {
        mAdapter = LaunchesListAdapter()
        mActivityBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    private fun initializeObservers() {
        mViewModel.fetchLaunchesFromServer(false).observe(this, Observer { kt ->
            mAdapter.setData(kt)
        })
        mViewModel.mShowApiError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(it).show()
        })
        mViewModel.mShowProgressBar.observe(this, Observer { bt ->
            if (bt) {
                mActivityBinding.progressBar.visibility = View.VISIBLE
                mActivityBinding.floatingActionButton.hide()
            } else {
                mActivityBinding.progressBar.visibility = View.GONE
                mActivityBinding.floatingActionButton.show()
            }
        })
        mViewModel.mShowNetworkError.observe(this, Observer {
            AlertDialog.Builder(this).setMessage(R.string.app_no_internet_msg).show()
        })
    }
}