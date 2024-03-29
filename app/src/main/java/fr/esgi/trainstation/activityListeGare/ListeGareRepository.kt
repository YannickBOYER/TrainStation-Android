package fr.esgi.trainstation.activityListeGare

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*

class ListeGareRepository {
    private val client = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getGaresFromLocation(latitude: String, longitude: String): ListeGareResponse {
        /*
        * Coordonnées geo de Lyon
        * lat : 45.761264
        * lon : 4.849556
        * rayon : 1000
        * */
        return client.get<ListeGareResponse>("https://data.sncf.com/api/records/1.0/search/") {
            parameter("dataset", "gares-de-voyageurs")
            parameter("geofilter.distance", "$latitude,$longitude,20000")
        }
    }
}