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

    suspend fun getGaresFromLocation(latitude: String = "45.761264", longitude: String = "4.849556", rayon: String = "2000"): ListeGareResponse {
        return client.get<ListeGareResponse>("https://data.sncf.com/api/records/1.0/search/") {
            parameter("dataset", "liste-des-gares")
            parameter("rows", 1000)
            parameter("q", "")
            parameter("geofilter.distance", "$latitude,$longitude,$rayon")
        }
    }
}