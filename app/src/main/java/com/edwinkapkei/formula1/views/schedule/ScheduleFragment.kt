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
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.databinding.FragmentScheduleBinding
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.utilities.ErrorProcessing
import com.edwinkapkei.formula1.views.schedule.adapter.ScheduleAdapter
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentScheduleViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@AndroidEntryPoint
class ScheduleFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var currentScheduleViewModelFactory: CurrentScheduleViewModelFactory

    @Inject
    lateinit var scheduleAdapter: ScheduleAdapter

    private lateinit var currentScheduleViewModel: CurrentScheduleViewModel

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewScheduleList()
    }

    private fun initViewModel() {
        currentScheduleViewModel =
            ViewModelProvider(this, currentScheduleViewModelFactory)[CurrentScheduleViewModel::class.java]
        currentScheduleViewModel.getCurrentSchedule(getCurrentYear())
    }

    private fun initRecyclerView() {
        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.purple_500))
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.scheduleRecycler.adapter = scheduleAdapter
        binding.scheduleRecycler.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    private fun viewScheduleList() {
        currentScheduleViewModel.currentSchedule.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Success -> {
                    hideProgressbar()
                    scheduleAdapter.differ.submitList(response.data.mRData.raceTable.races.toList())
                }
                is RequestState.Error -> {
                    hideProgressbar()
                    ErrorProcessing.processHttpErrorCodes(code = response.code, view = binding.root)
                }
                is RequestState.Exception -> {
                    hideProgressbar()
                    Snackbar.make(
                        binding.root,
                        response.e.message.toString(),
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
        currentScheduleViewModel.getCurrentSchedule(getCurrentYear())
    }

    private fun showProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onPause() {
        super.onPause()
        binding.swipeRefreshLayout.isEnabled = false
    }

    override fun onResume() {
        super.onResume()
        binding.swipeRefreshLayout.isEnabled = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}