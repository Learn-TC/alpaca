package ollama.exceptions

class OllamaModelNotFoundException(modelName: String) : Exception("Ollama model '$modelName' not found")
