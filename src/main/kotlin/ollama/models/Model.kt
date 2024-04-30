package ollama.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ollama.utils.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class Models(
    val models: List<Model>,
)

@Serializable
data class Model(
    val name: String,
    @SerialName("modified_at")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedAt: LocalDateTime,
    val size: Long,
    val digest: String,
    val details: ModelDetails,
){
    val id: String
        get() = digest.takeLast(12)

    val formattedSize: String
        get() {
            val units = arrayOf("bytes", "KB", "MB", "GB", "TB")
            var unitIndex = 0
            var sizeValue = size.toDouble()

            while (sizeValue >= 1024.0 && unitIndex < units.lastIndex) {
                sizeValue /= 1024.0
                unitIndex++
            }

            return "%.1f %s".format(sizeValue, units[unitIndex])
        }
}

@Serializable
data class ShowModel(
    val modelfile: String,
    val parameters: String,
    val template: String,
    val details: ModelDetails,
)

@Serializable
data class ModelDetails(
    val format: String,
    val family: String,
    val families: List<String>?,
    @SerialName("parameter_size")
    val parameterSize: String,
    @SerialName("quantization_level")
    val quantizationLevel: String,
)
