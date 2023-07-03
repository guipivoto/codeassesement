package com.pivoto.codeassessment.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.pivoto.codeassessment.android.parcelable
import com.pivoto.codeassessment.events.Event
import com.pivoto.codeassessment.navigation.Destination
import com.pivoto.codeassessment.navigation.NavigationResult
import com.pivoto.codeassessment.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    /**
     * The view model associated with this fragment
     */
    abstract val viewModel: BaseViewModel

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.eventsChannel.collectFromStart {
            when(it) {
                is Event.Navigate -> navigate(it.destination)
            }
        }

        viewModel.screenResult.collectFromStart {
            if (it != null) {
                setResult(it)
            }
        }

        // If I'm being re-created, start to listen to fragment results again
        registerForResultListeners()
    }

    private fun navigate(destination: Destination) {
        findNavController().navigate(
            destination.destinationId,
            Bundle().apply {
                putString(NAVIGATION_LISTENER_KEY, destination.resultListenerKey)
                destination.getArgsBundle()?.let { putAll(it) }
            }
        )
        registerForResultListeners()
    }

    private fun registerForResultListeners() {
        viewModel.navigationResultListeners.forEach { key ->
            clearFragmentResultListener(key)
            setFragmentResultListener(key) { listenerKey, bundle ->
                // Retrieve the result added via "setResult". Then, share the result with the viewmodel
                bundle.parcelable<NavigationResult>(NAVIGATION_RESULT_EXTRA)?.let {
                    viewModel.handleNavigationResult(listenerKey, it)
                }
            }
        }
    }

    private fun setResult(navigationResult: NavigationResult) {
        // Put the result as a parcelable extra. Then, it can be retrieved later
        val listenerKey = arguments?.getString(NAVIGATION_LISTENER_KEY) ?: return
        setFragmentResult(listenerKey, Bundle().apply { putParcelable(NAVIGATION_RESULT_EXTRA, navigationResult) })
    }

    /**
     * Helpful extension to start collecting a flow as soon as the Fragment reaches the STARTED state
     */
    fun <T> Flow<T>.collectFromStart(function: suspend (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectFromStart.collect {
                    function(it)
                }
            }
        }
    }

    companion object {
        const val NAVIGATION_RESULT_EXTRA = "navigation_result_extra"
        const val NAVIGATION_LISTENER_KEY = "navigation_listener_key"
    }
}
