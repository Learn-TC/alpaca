package ollama.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PullResponse(
    val status: PullResponseStatus,
    val digest: String?,
    val total: Long?,
    val completed: Long?,
    val error: String?,
) {
    fun calculateDownloadPercentage(): Float? {
        if (total == null || completed == null) return null
        return (completed / total * 100).toFloat()
    }
}

@Serializable
enum class PullResponseStatus {
    @SerialName("pulling manifest") PULLING_MANIFEST,
    @SerialName("downloading digestname") DOWNLOADING,
    @SerialName("verifying sha256 digest") VERIFYING_DIGEST,
    @SerialName("writing manifest") WRITING_MANIFEST,
    @SerialName("removing any unused layers") REMOVING_UNUSED_LAYERS,
    @SerialName("success") SUCCESS;

    fun isSuccessful(): Boolean = this == SUCCESS

}
