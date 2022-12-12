package com.example.moviepagination.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.moviepagination.R
import com.example.moviepagination.utils.isOnline
import org.koin.core.component.KoinScopeComponent
import org.koin.core.scope.Scope

abstract class BaseFragment<T : ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> T
) : Fragment(), KoinScopeComponent {

    abstract override val scope: Scope
    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected var isNetworkAvailable: Boolean = true

    override fun onStart() {
        super.onStart()
        checkNetworkStatus()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    open fun showError(error: Throwable) {
        Toast.makeText(
            requireContext(),
            "${getString(R.string.error)} ${error.message}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun checkNetworkStatus() {
        isNetworkAvailable = isOnline(requireContext())
        if (!isNetworkAvailable) {
            showErrorConnection()
        }
    }

    open fun showErrorConnection() {
        Toast.makeText(
            requireContext(),
            getString(R.string.no_internet_error),
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}