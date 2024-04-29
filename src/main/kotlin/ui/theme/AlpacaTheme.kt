package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.jewel.foundation.GlobalColors
import org.jetbrains.jewel.foundation.theme.JewelTheme
import org.jetbrains.jewel.intui.standalone.styling.Default
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.intui.standalone.theme.createDefaultTextStyle
import org.jetbrains.jewel.intui.standalone.theme.dark
import org.jetbrains.jewel.intui.standalone.theme.darkThemeDefinition
import org.jetbrains.jewel.intui.window.decoratedWindow
import org.jetbrains.jewel.intui.window.styling.dark
import org.jetbrains.jewel.ui.ComponentStyling
import org.jetbrains.jewel.ui.component.styling.DropdownColors
import org.jetbrains.jewel.ui.component.styling.DropdownStyle
import org.jetbrains.jewel.window.styling.TitleBarColors
import org.jetbrains.jewel.window.styling.TitleBarStyle

@Composable
fun AlpacaTheme(content: @Composable () -> Unit) {
    val textStyle = JewelTheme.createDefaultTextStyle(fontFamily = FontFamily.Monospace)

    val themeDefinition = JewelTheme.darkThemeDefinition(
        colors = GlobalColors.dark(
            paneBackground = Color(0xffffffff)
        ),
        defaultTextStyle = textStyle,
    )

    val backgroundColor = Color(0xFF181616) //TODO
    val borderColor = Color(0xff2A2A27) //TODO

    IntUiTheme(
        theme = themeDefinition,
        styling = ComponentStyling.dark(
            undecoratedDropdownStyle = DropdownStyle.Default.dark(
                colors = DropdownColors.Default.dark(background = backgroundColor)
            ),
        ).decoratedWindow(
            titleBarStyle = TitleBarStyle.dark(
                colors = TitleBarColors.dark(
                    backgroundColor = backgroundColor,
                    inactiveBackground = backgroundColor,
                    borderColor = borderColor,
                )
            )
        ),
        content = content,
    )
}
