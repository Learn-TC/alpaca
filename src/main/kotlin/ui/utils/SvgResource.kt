package ui.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.ResourceLoader
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.unit.Density
import java.io.InputStream

@OptIn(ExperimentalComposeUiApi::class)
fun svgResource(
    resourcePath: String,
    loader: ResourceLoader = ResourceLoader.Default,
): Painter =
    loader
        .load(resourcePath)
        .use { stream: InputStream -> loadSvgPainter(stream, Density(1f)) }