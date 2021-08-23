package com.ewake.myfinance.ui.fragment.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewake.myfinance.R
import com.ewake.myfinance.databinding.FragmentMainPageBinding
import com.ewake.myfinance.ui.base.BaseFragment
import com.ewake.myfinance.ui.fragment.mainpage.adapter.CategoriesAdapter
import com.ewake.myfinance.ui.model.BudgetModel
import com.ewake.myfinance.ui.model.CategoryModel
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class MainPageFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainPageViewModel> { viewModelFactory }

    private var _binding: FragmentMainPageBinding? = null
    private val binding: FragmentMainPageBinding
        get() = _binding!!

    private val categoriesAdapter = CategoriesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.mainPageComponent().create().inject(this)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)

        binding.apply {
            add.setOnClickListener {
                viewModel.onAddButtonClicked()
            }
            minus.setOnClickListener {
                viewModel.onMinusButtonClicked()
            }
            categories.apply {
                adapter = categoriesAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.apply {
            budgetLiveData.observe({ viewLifecycleOwner.lifecycle }, ::setUserData)
            categoriesLiveData.observe(viewLifecycleOwner, ::setCategories)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserData(model: BudgetModel) {
        binding.apply {
            income.text = getString(R.string.fragment_main_page_income, model.income.toString())
            outcome.text =
                getString(R.string.fragment_main_page_outcome, model.outcome.toString())
            balance.text =
                getString(R.string.fragment_main_page_balance, model.balance.toString())
            transferBalance.text = getString(
                R.string.fragment_main_page_balance_transfer,
                model.transferBalance.toString()
            )
        }
    }

    private fun setCategories(list: List<CategoryModel>) {
        categoriesAdapter.items = list
    }
}