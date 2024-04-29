import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.application
import model.Model
import org.jetbrains.jewel.window.DecoratedWindow
import ui.components.SidebarView
import ui.components.TitleBarEvent
import ui.components.TitleBarView
import ui.theme.AlpacaTheme

fun main() = application {
    AlpacaTheme {
        var sidebarExpanded by mutableStateOf(true)
        val models = listOf(
            Model(name = "codegemma:7b", digest = "1872317263187326ca966f70c13f"),
            Model(name = "llama3:8b", digest = "2938ynr2983e71a106a91016")
        )

        var selectedModel by remember { mutableStateOf(models.first()) }

        DecoratedWindow(onCloseRequest = ::exitApplication) {
            TitleBarView(
                selectedModel = selectedModel,
                models = models,
                sidebarExpanded = sidebarExpanded,
                onEvent = { event ->
                    when (event) {
                        TitleBarEvent.ToggleSidebar -> { sidebarExpanded = !sidebarExpanded }
                        TitleBarEvent.OnNotification -> { }
                        TitleBarEvent.OnSettings -> { }
                        TitleBarEvent.OnOpenModelDialog -> { }
                        is TitleBarEvent.OnModelSelected -> { selectedModel = event.model }
                    }
                },
            )

            Row(modifier = Modifier.fillMaxSize()) {
                SidebarView(expanded = sidebarExpanded, onNewChat = { /*TODO*/ })

                //Chat
                Box(modifier = Modifier.fillMaxHeight().weight(1f).background(Color(0xff121212))) {

                }
            }
        }
    }
}
