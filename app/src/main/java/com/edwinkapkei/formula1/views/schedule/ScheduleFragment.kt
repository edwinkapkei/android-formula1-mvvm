package com.edwinkapkei.formula1.views.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.databinding.FragmentScheduleBinding
import com.edwinkapkei.formula1.views.MainActivity
import com.edwinkapkei.formula1.views.schedule.adapter.ScheduleAdapter
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class ScheduleFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var currentScheduleViewModelFactory: CurrentScheduleViewModelFactory
    private lateinit var currentScheduleViewModel: CurrentScheduleViewModel
    private lateinit var binding: FragmentScheduleBinding
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScheduleBinding.bind(view)

        initRecyclerView()
        viewScheduleList()
    }

    private fun initViewModel() {
        currentScheduleViewModel =
            ViewModelProvider(this, currentScheduleViewModelFactory)[CurrentScheduleViewModel::class.java]
        currentScheduleViewModel.getCurrentSchedule()
    }

    private fun initRecyclerView() {
        //binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.purple_500))
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        scheduleAdapter = ScheduleAdapter()
        binding.scheduleRecycler.adapter = scheduleAdapter
        binding.scheduleRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
    private fun viewScheduleList() {
        currentScheduleViewModel.currentSchedule.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Success -> {
                    hideProgressbar()
                    response.data?.let {
                        scheduleAdapter.differ.submitList(it.mRData.raceTable.races.toList())
                    }
                }
                is RequestState.Error -> {
                    hideProgressbar()
                    if (response.message != null)
                        Snackbar.make(binding.root, response.message, Snackbar.LENGTH_SHORT).show()
                    else
                        Snackbar.make(
                            binding.root,
                            "We could not complete your request. Please try again",
                            Snackbar.LENGTH_SHORT
                        ).show()
                }
                is RequestState.Loading -> {
                    showProgressbar()
                }
            }
        }
    }

    override fun onRefresh() {
        currentScheduleViewModel.getCurrentSchedule()
    }

    private fun showProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = false
    }
}