package com.example.yjahz.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.yjahz.databinding.FragmentHomeBinding
import com.example.yjahz.ui.home.adapters.CategoryAdapter
import com.example.yjahz.ui.home.adapters.PopularSellerAdapter
import com.example.yjahz.ui.home.adapters.TrendingSellerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val categoryAdapter: CategoryAdapter by inject()
    private val trendingSellerAdapter: TrendingSellerAdapter by inject()
    private val popularSellerAdapter: PopularSellerAdapter by inject()

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdaptersOnViews(categoryAdapter, trendingSellerAdapter, popularSellerAdapter)

        viewModel.loadDataFromApi()

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.setData(it)
            TransitionManager.beginDelayedTransition(binding.categoriesRecyclerView, Slide())
        }

        viewModel.popularSellers.observe(viewLifecycleOwner) {
            popularSellerAdapter.setData(it)
            TransitionManager.beginDelayedTransition(binding.popularRecyclerView, Slide())
        }

        viewModel.trendingSellers.observe(viewLifecycleOwner) {
            trendingSellerAdapter.setData(it)
            TransitionManager.beginDelayedTransition(binding.trendingRecycleView, Slide())
        }


        val args = HomeFragmentArgs.fromBundle(requireArguments())

        val name = "Hello ${args.name}"
        binding.nameText.text = name

        binding.searchView.setOnClickListener {
            binding.searchEditTextText.isEnabled = true
            binding.searchEditTextText.requestFocus()
        }

        binding.backButton.setOnClickListener {
            if (!view.findNavController().popBackStack()) {
                requireActivity().finish()
            }
        }

    }

    private fun setAdaptersOnViews(
        categoryAdapter: CategoryAdapter,
        trendingSellerAdapter: TrendingSellerAdapter,
        popularSellerAdapter: PopularSellerAdapter
    ) {
        val llm1 = LinearLayoutManager(requireContext())
        llm1.orientation = LinearLayoutManager.HORIZONTAL

        val llm2 = LinearLayoutManager(requireContext())
        llm2.orientation = LinearLayoutManager.HORIZONTAL

        val llm3 = LinearLayoutManager(requireContext())
        llm3.orientation = LinearLayoutManager.HORIZONTAL

        binding.categoriesRecyclerView.layoutManager = llm1
        binding.popularRecyclerView.layoutManager = llm2
        binding.trendingRecycleView.layoutManager = llm3

        binding.categoriesRecyclerView.adapter = categoryAdapter
        binding.trendingRecycleView.adapter = trendingSellerAdapter
        binding.popularRecyclerView.adapter = popularSellerAdapter
    }


}