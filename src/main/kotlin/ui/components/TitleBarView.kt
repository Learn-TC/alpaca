package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.jewel.intui.core.theme.IntUiDarkTheme
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.ui.component.Icon
import org.jetbrains.jewel.ui.component.IconButton
import org.jetbrains.jewel.ui.component.Text
import org.jetbrains.jewel.window.DecoratedWindowScope
import org.jetbrains.jewel.window.TitleBar
import org.jetbrains.jewel.window.styling.TitleBarColors
import org.jetbrains.jewel.window.styling.TitleBarStyle
import ui.utils.svgResource

sealed interface TitleBarEvent {
    data object ToggleSidebar : TitleBarEvent
    data object OnNotification : TitleBarEvent
    data object OnSettings : TitleBarEvent
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DecoratedWindowScope.TitleBarView(
    sidebarExpanded: Boolean,
    onEvent: (event: TitleBarEvent) -> Unit,
) {
    val backgroundColor = Color(0xFF181616) //TODO
    val borderColor = Color(0xff2A2A27) //TODO
    val primary = IntUiDarkTheme.colors.yellow.first() //TODO

    TitleBar(
        gradientStartColor = primary,
        style = TitleBarStyle.dark(
            colors = TitleBarColors.dark(
                backgroundColor = backgroundColor,
                borderColor = borderColor,
            )
        ),
    ) {
        val menuPainter = svgResource(resourcePath = "icons/menu.svg")
        val notificationPainter = svgResource(resourcePath = "icons/notification.svg")
        val slidersPainter = svgResource(resourcePath = "icons/sliders.svg")

        Row(
            modifier = Modifier.align(Alignment.Start).padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                onClick = { onEvent(TitleBarEvent.ToggleSidebar) },
            ) {
                Icon(
                    painter = menuPainter,
                    contentDescription = ""
                )
            }

            Text(text = "Alpaca", modifier = Modifier.padding(start = 12.dp))
        }

        Row(
            modifier = Modifier.align(Alignment.End).padding(vertical = 6.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IconButton(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                onClick = { onEvent(TitleBarEvent.OnNotification) },
            ) {
                Icon(
                    painter = notificationPainter,
                    contentDescription = ""
                )
            }

            IconButton(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                onClick = { onEvent(TitleBarEvent.OnSettings) },
            ) {
                Icon(
                    painter = slidersPainter,
                    contentDescription = ""
                )
            }
        }
    }
}
