package com.ewake.myfinance.ui.fragment.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.ewake.myfinance.databinding.FragmentSplashScreenBinding
import com.ewake.myfinance.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

/**
 * @author Nikolaevsky Dmitry (@d.nikolaevskiy)
 */
class SplashScreenFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SplashScreenViewModel> { viewModelFactory }

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding: FragmentSplashScreenBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.splashScreenComponent().create().inject(this)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        viewModel.apply {
            navigateLiveData.observe({ viewLifecycleOwner.lifecycle }, ::navigate)
            errorLiveData.observe(viewLifecycleOwner, ::showError)
        }

        return binding.root
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun showError(error: String) {
        Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}