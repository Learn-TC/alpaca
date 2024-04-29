package model

//TODO: uncomment when model repository is implemented
data class Model(
//    val details: ModelDetails,
    val digest: String,
//    val modifiedAt: String,
    val name: String,
//    val size: Long,
) {
    val id: String
        get() = digest.takeLast(12)
}

data class ModelDetails(
    val families: Any,
    val family: String,
    val format: String,
    val parameterSize: String,
    val quantizationLevel: String,
)
