package com.pivoto.codeassessment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pivoto.codeassessment.events.Event
import com.pivoto.codeassessment.navigation.Destination
import com.pivoto.codeassessment.navigation.NavigationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    // Channel for single time events
    private val _eventChannel: Channel<Event> = Channel()

    /**
     * Single time events that triggers single time actions on the fragment like SnackBar messages etc
     */
    val eventsChannel: Flow<Event> = _eventChannel.receiveAsFlow()

    private val _navigationResultListener: MutableList<String> = mutableListOf()

    /**
     * The list of fragment results this "ViewModel <-> Fragment" pair is listening to
     */
    val navigationResultListeners: List<String> = _navigationResultListener

    private val _screenResult: MutableStateFlow<NavigationResult?> = MutableStateFlow(null)

    /**
     * The desired result that should be set before finishing this screen
     * Store the state in a flow and this way, the result can be recovered after screen rotation
     */
    val screenResult: Flow<NavigationResult?> = _screenResult

    /**
     * Navigate to another screen and ignore any result produced by the next destination
     * @param destination Navigation destination. Screen that should be invoked
     */
    protected fun navigate(destination: Destination) {
        viewModelScope.launch { _eventChannel.send(Event.Navigate(destination)) }
    }

    /**
     * Navigate to another destination and register to listen the result of the next screen
     * @param destination Navigation destination. Screen that should be invoked
     */
    protected fun navigateForResult(destination: Destination) {
        viewModelScope.launch { _eventChannel.send(Event.Navigate(destination)) }
        _navigationResultListener.add(destination.resultListenerKey)
    }

    /**
     * Set a result for this screen
     * @param [result] Navigation result of this screen
     */
    protected fun setResult(result: NavigationResult) {
        _screenResult.value = result
    }

    /**
     * Method invoked when this view model is expecting the result of another screen (the result of
     * a navigation).
     * It is invoked when an fragment is finished and this view model is moving to the top
     * of the back stack.
     * @param resultKey Key of the navigation result being produced
     * @param result Navigation result produced by the fragment that is finishing
     */
    fun handleNavigationResult(resultKey: String, result: NavigationResult) {
        _navigationResultListener.remove(resultKey)
        onNavigationResult(result)
    }

    /**
     * Method invoked when this view model is expecting the result of another screen (the result of
     * a navigation).
     * It is invoked when an fragment is finished and this view model is moving to the top
     * of the back stack.
     * @param result The navigation result produced by the fragment that is finishing
     */
    open fun onNavigationResult(result: NavigationResult) = Unit
}