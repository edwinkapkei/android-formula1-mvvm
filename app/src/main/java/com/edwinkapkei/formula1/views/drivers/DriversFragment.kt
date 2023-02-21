package com.edwinkapkei.formula1.views.drivers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.databinding.FragmentConstructorsBinding
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.databinding.FragmentDriversBinding
import com.edwinkapkei.formula1.databinding.FragmentScheduleBinding
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.utilities.ErrorProcessing
import com.edwinkapkei.formula1.views.drivers.adapter.DriversAdapter
import com.edwinkapkei.formula1.views.viewmodel.CurrentDriversViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentDriversViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class DriversFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var currentDriversViewModelFactory: CurrentDriversViewModelFactory

    @Inject
    lateinit var driversAdapter: DriversAdapter

    private lateinit var currentDriversViewModel: CurrentDriversViewModel
    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.e("onCreate")
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewDriversList()
    }

    private fun initViewModel() {
        currentDriversViewModel = ViewModelProvider(
            this,
            currentDriversViewModelFactory
        )[CurrentDriversViewModel::class.java]
        currentDriversViewModel.getCurrentDrivers(getCurrentYear())
    }

    private fun initRecyclerView() {
        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.purple_500))
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.driversRecycler.adapter = driversAdapter
        binding.driversRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun viewDriversList() {
        currentDriversViewModel.currentDrivers.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Success -> {
                    hideProgressbar()
                    driversAdapter.differ.submitList(response.data)
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
        currentDriversViewModel.getCurrentDrivers(getCurrentYear())
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