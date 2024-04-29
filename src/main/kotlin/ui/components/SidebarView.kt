package ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.jewel.ui.component.Icon
import org.jetbrains.jewel.ui.component.IconButton
import org.jetbrains.jewel.ui.component.Text
import ui.utils.svgResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SidebarView(
    expanded: Boolean,
    onNewChat: () -> Unit,
) {
    val width by animateDpAsState(targetValue = if (expanded) 190.dp else 48.dp)
    val alignment: Alignment.Horizontal = if (expanded) Alignment.Start else Alignment.CenterHorizontally
    val padding = if (expanded) PaddingValues(horizontal = 4.dp, vertical = 12.dp) else PaddingValues(vertical = 12.dp)

    val backgroundColor = Color(0xFF181616) //TODO
    val borderColor = Color(0xff2A2A27) //TODO
    val primary = Color(0xffBFBA9D) //TODO

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .background(backgroundColor)
            .drawBehind {
                drawLine(borderColor, start = Offset(size.width, 0f), end = Offset(size.width, size.height))
            }
            .padding(padding),
        horizontalAlignment = alignment,
    ) {
        val addPainter = svgResource("/icons/plus.svg")

        IconButton(modifier = Modifier, onClick = onNewChat) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    painter = addPainter,
                    contentDescription = "",
                )

                if (expanded) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp).padding(end = 12.dp),
                        text = "New chat",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = primary,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}
