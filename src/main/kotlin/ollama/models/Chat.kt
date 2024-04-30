package ollama.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ollama.utils.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class Message(
    val role: Role,
    var content: String,
    val images: List<String>? = null
)

@Serializable
enum class Role {
    @SerialName("user") USER,
    @SerialName("system") SYSTEM,
    @SerialName("assistant") ASSISTANT
}

@Serializable
data class ChatRequest(
    val model: String,
    val messages: List<Message>
)

@Serializable
data class ChatResponse(
    val model: String,
    @SerialName("created_at")
    @Serializable(LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    val message: Message,
    val done: Boolean
)
