package domain

import model.Model

interface ModelRepository {
    fun getPulledModels(): List<Model>
}

//TODO: delete this after proper implementation
class MockedModelRepository: ModelRepository {
    private val models = listOf(
        Model(name = "codegemma:7b", digest = "1872317263187326ca966f70c13f"),
        Model(name = "llama3:8b", digest = "2938ynr2983e71a106a91016")
    )

    override fun getPulledModels(): List<Model> = models
}
