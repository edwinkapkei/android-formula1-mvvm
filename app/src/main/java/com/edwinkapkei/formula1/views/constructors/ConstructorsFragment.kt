package com.edwinkapkei.formula1.views.constructors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.edwinkapkei.formula1.R
import com.edwinkapkei.formula1.databinding.FragmentConstructorsBinding
import com.edwinkapkei.formula1.utilities.CustomDateFormatter.getCurrentYear
import com.edwinkapkei.formula1.utilities.ErrorProcessing
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.views.constructors.adapter.ConstructorsAdapter
import com.edwinkapkei.formula1.views.viewmodel.CurrentConstructorsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConstructorsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var constructorsAdapter: ConstructorsAdapter

    private val currentConstructorsViewModel: CurrentConstructorsViewModel by viewModels()
    private var _binding: FragmentConstructorsBinding? = null
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
        _binding = FragmentConstructorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewDriversList()
    }

    private fun initViewModel() {
        currentConstructorsViewModel.getCurrentConstructors(getCurrentYear())
    }

    private fun initRecyclerView() {
        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.purple_500))
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.constructorsRecycler.adapter = constructorsAdapter
        binding.constructorsRecycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun viewDriversList() {
        currentConstructorsViewModel.currentConstructors.observe(viewLifecycleOwner) { response ->
            when (response) {
                is RequestState.Success -> {
                    hideProgressbar()
                    val standingsList = response.data.mRData.standingsTable.standingsLists
                    if (standingsList.isNotEmpty()) {
                        constructorsAdapter.differ.submitList(response.data.mRData.standingsTable.standingsLists[0].constructorStandings)
                    }
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
        currentConstructorsViewModel.getCurrentConstructors(getCurrentYear())
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