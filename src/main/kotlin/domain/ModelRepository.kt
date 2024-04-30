package domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ollama.Ollama
import ollama.models.Model

interface ModelRepository {
    suspend fun getPulledModels(): List<Model>
}

class ModelRepositoryImpl: ModelRepository {
    private val ollama = Ollama()

    override suspend fun getPulledModels(): List<Model> = withContext(Dispatchers.IO) {
        return@withContext ollama.listModels().models
    }
}
