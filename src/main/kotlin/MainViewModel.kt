import domain.ModelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ollama.models.Model
import ui.components.TitleBarEvent
import utils.ViewModel

class MainViewModel(
    private val modelRepository: ModelRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        fetchPulledModels()
    }

    fun handleTitleBarEvents(event: TitleBarEvent) {
        when (event) {
            TitleBarEvent.ToggleSidebar -> toggleSidebarState()
            TitleBarEvent.OnNotification -> { /*TODO*/ }
            TitleBarEvent.OnSettings -> { /*TODO*/ }
            is TitleBarEvent.OnModelSelected -> updateSelectedModel(event.model)
        }
    }

    private fun fetchPulledModels() {
        viewModelScope.launch {
            val models = modelRepository.getPulledModels()
            _uiState.update { it.copy(models = models) }
        }
    }

    private fun toggleSidebarState() {
        _uiState.update {
            val expanded = it.copy().sidebarExpanded
            it.copy(sidebarExpanded = !expanded)
        }
    }

    private fun updateSelectedModel(selectedModel: Model) {
        _uiState.update { it.copy(selectedModel = selectedModel) }
    }
}

data class MainUiState(
    val sidebarExpanded: Boolean = true,
    val models: List<Model> = emptyList(),
    val selectedModel: Model? = models.firstOrNull(),
)
