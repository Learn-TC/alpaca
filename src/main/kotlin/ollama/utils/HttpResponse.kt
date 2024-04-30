package ollama.utils

import io.ktor.client.statement.*

fun HttpResponse.isSuccessful(): Boolean {
    return this.status.value in 200..299
}
