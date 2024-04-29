import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.application
import domain.MockedModelRepository
import org.jetbrains.jewel.window.DecoratedWindow
import ui.components.SidebarView
import ui.components.TitleBarView
import ui.theme.AlpacaTheme

fun main() = application {
    AlpacaTheme {
        val mockedRepository = MockedModelRepository()
        val viewModel = MainViewModel(mockedRepository)
        val uiState by viewModel.uiState.collectAsState()

        DecoratedWindow(onCloseRequest = ::exitApplication) {
            TitleBarView(
                selectedModel = uiState.selectedModel,
                models = uiState.models,
                sidebarExpanded = uiState.sidebarExpanded,
                onEvent = viewModel::handleTitleBarEvents,
            )

            Row(modifier = Modifier.fillMaxSize()) {
                SidebarView(
                    expanded = uiState.sidebarExpanded,
                    onNewChat = { /*TODO*/ },
                )

                //Chat
                Box(modifier = Modifier.fillMaxHeight().weight(1f).background(Color(0xff121212))) {

                }
            }
        }
    }
}
