package fr.esgi.trainstation.activityListeGare

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*

object ListeGareApi {
    private val client = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getCatFact(): CatFactResponse {
        return client.get("https://catfact.ninja/fact")
    }
}