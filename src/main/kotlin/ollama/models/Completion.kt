package ollama.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ollama.utils.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class CompletionRequest(
    val model: String,
    val prompt: String,
)

@Serializable
data class CompletionResponse(
    val model: String,
    @SerialName("created_at")
    @Serializable(LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    val response: String,
    val done: Boolean,
)
