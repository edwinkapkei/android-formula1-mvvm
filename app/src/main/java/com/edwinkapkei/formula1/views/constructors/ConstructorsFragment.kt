package com.edwinkapkei.formula1.views.constructors

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
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.databinding.FragmentConstructorsBinding
import com.edwinkapkei.formula1.databinding.FragmentDriversBinding
import com.edwinkapkei.formula1.views.constructors.adapter.ConstructorsAdapter
import com.edwinkapkei.formula1.views.drivers.adapter.DriversAdapter
import com.edwinkapkei.formula1.views.viewmodel.CurrentConstructorsViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentConstructorsViewModelFactory
import com.edwinkapkei.formula1.views.viewmodel.CurrentDriversViewModel
import com.edwinkapkei.formula1.views.viewmodel.CurrentDriversViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConstructorsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener  {


    @Inject
    lateinit var currentConstructorsViewModelFactory: CurrentConstructorsViewModelFactory

    @Inject
    lateinit var constructorsAdapter: ConstructorsAdapter

    private lateinit var currentConstructorsViewModel: CurrentConstructorsViewModel
    private lateinit var binding: FragmentConstructorsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_constructors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConstructorsBinding.bind(view)

        initRecyclerView()
        viewDriversList()
    }

    private fun initViewModel() {
        currentConstructorsViewModel = ViewModelProvider(
            this,
            currentConstructorsViewModelFactory
        )[CurrentConstructorsViewModel::class.java]
        currentConstructorsViewModel.getCurrentConstructors()
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
                    response.data?.let {
                        constructorsAdapter.differ.submitList(it.mRData.standingsTable.standingsLists[0].constructorStandings)
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
        currentConstructorsViewModel.getCurrentConstructors()
    }

    private fun showProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    private fun hideProgressbar() {
        binding.swipeRefreshLayout.isRefreshing = false
    }
}