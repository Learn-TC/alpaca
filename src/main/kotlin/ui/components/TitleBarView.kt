package ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import model.Model
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.styling.dark
import org.jetbrains.jewel.ui.component.*
import org.jetbrains.jewel.ui.component.styling.DropdownStyle
import org.jetbrains.jewel.ui.component.styling.MenuColors
import org.jetbrains.jewel.ui.component.styling.MenuItemColors
import org.jetbrains.jewel.ui.component.styling.MenuStyle
import org.jetbrains.jewel.ui.theme.dropdownStyle
import org.jetbrains.jewel.window.DecoratedWindowScope
import org.jetbrains.jewel.window.TitleBar
import ui.utils.svgResource

sealed interface TitleBarEvent {
    data object ToggleSidebar : TitleBarEvent
    data object OnNotification : TitleBarEvent
    data object OnSettings : TitleBarEvent
    data object OnOpenModelDialog : TitleBarEvent
    data class OnModelSelected(val model: Model) : TitleBarEvent
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DecoratedWindowScope.TitleBarView(
    selectedModel: Model,
    models: List<Model>,
    sidebarExpanded: Boolean,
    onEvent: (event: TitleBarEvent) -> Unit,
) {
    TitleBar {
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
                    contentDescription = "menu icon"
                )
            }

            Text(text = "Alpaca", modifier = Modifier.padding(start = 12.dp))

            val dropdownStartPadding by animateDpAsState(
                targetValue = if (sidebarExpanded) 100.dp else 12.dp,
            )

            ModelDropdown(
                modifier = Modifier.padding(start = dropdownStartPadding),
                selectedModel = selectedModel,
                models = models,
                onModelClick = { model -> TitleBarEvent.OnModelSelected(model) },
            )
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
                    contentDescription = "notification icon"
                )
            }

            IconButton(
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                onClick = { onEvent(TitleBarEvent.OnSettings) },
            ) {
                Icon(
                    painter = slidersPainter,
                    contentDescription = "settings icon"
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ModelDropdown(
    modifier: Modifier = Modifier,
    menuModifier: Modifier = Modifier,
    selectedModel: Model,
    models: List<Model>,
    onModelClick: (Model) -> Unit,
) {
    // TODO: move colors
    val backgroundColor = Color(0xFF181616)
    val borderColor = Color(0xff2A2A27)
    val groupTitleColor = Color(0xff7C7973)
    val groupContentFocusBackground = Color(0xff282727)

    val style = JewelTheme.dropdownStyle
    val customStyle = DropdownStyle(
        colors = style.colors,
        metrics = style.metrics,
        icons = style.icons,
        textStyle = style.textStyle,
        menuStyle = MenuStyle.dark(
            colors = MenuColors.dark(
                background = backgroundColor,
                border = borderColor,
                itemColors = MenuItemColors.dark(
                    background = Color.Unspecified,
                    backgroundFocused = groupContentFocusBackground,
                ),
            ),
        ),
    )

    Dropdown(
        modifier = modifier,
        menuModifier = menuModifier then Modifier.width(360.dp),
        style = customStyle,
        menuContent = {
            passiveItem {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(modifier = Modifier.weight(1f), text = "NAME", color = groupTitleColor)
                    Text(
                        modifier = Modifier.width(108.dp).padding(horizontal = 8.dp),
                        text = "ID",
                        color = groupTitleColor,
                    )
                    Text(
                        modifier = Modifier.widthIn(min = 54.dp),
                        text = "SIZE",
                        textAlign = TextAlign.Start,
                        color = groupTitleColor
                    )
                }
            }

            items(items = models, isSelected = { it == selectedModel }, onItemClick = onModelClick) { model ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = model.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(modifier = Modifier.width(108.dp).padding(horizontal = 8.dp), text = model.id)
                    Text(modifier = Modifier.widthIn(min = 54.dp), text = "5.0 GB", textAlign = TextAlign.Start)
                }
            }

            separator()

            passiveItem {
                var text by remember { mutableStateOf("") }

                Row(
                    modifier = Modifier.padding(start = 8.dp, end = 12.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TextArea(
                        modifier = Modifier.weight(1f),
                        value = text,
                        onValueChange = { text = it },
                        undecorated = true,
                        placeholder = {
                            Text("model name to download", color = groupTitleColor)
                        },
                        maxLines = 1,
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            painter = svgResource("icons/download.svg"),
                            contentDescription = "download icon",
                        )
                    }
                }

            }
        },
    ) {
        Row(modifier = Modifier.widthIn(max = 180.dp)) {
            Text(text = selectedModel.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}
