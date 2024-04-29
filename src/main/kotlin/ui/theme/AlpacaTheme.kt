package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.intui.standalone.theme.createDefaultTextStyle
import org.jetbrains.jewel.intui.standalone.theme.darkThemeDefinition
import org.jetbrains.jewel.intui.standalone.theme.default
import org.jetbrains.jewel.intui.window.decoratedWindow
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.ui.ComponentStyling
import org.jetbrains.jewel.window.styling.TitleBarStyle


@Composable
fun AlpacaTheme(content: @Composable () -> Unit) {
    val textStyle = JewelTheme.createDefaultTextStyle(fontFamily = FontFamily.Monospace)
    val themeDefinition = JewelTheme.darkThemeDefinition(defaultTextStyle = textStyle)

    IntUiTheme(
        theme = themeDefinition,
        styling = ComponentStyling.default().decoratedWindow(
            titleBarStyle = TitleBarStyle.dark()
        ),
        content = content
    )
}