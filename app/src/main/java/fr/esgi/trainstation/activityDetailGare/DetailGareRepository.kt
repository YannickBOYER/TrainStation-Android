package fr.esgi.trainstation.activityDetailGare

import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse
import fr.esgi.trainstation.activityListeGare.ListeGareResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.json.Json

class DetailGareRepository {
    private val client = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getHorairesFromGare(nomGare: String): HoraireGareResponse {
        return client.get<HoraireGareResponse>("https://ressources.data.sncf.com/api/explore/v2.1/catalog/datasets/horaires-des-gares1/records?limit=20&refine=nom_normal:$nomGare")
    }
}