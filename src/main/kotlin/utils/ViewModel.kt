package utils

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

open class ViewModel {
    protected open val viewModelScope = MainScope()

    open fun onDispose() {
        viewModelScope.cancel()
    }
}
