package fr.esgi.trainstation.activityDetailGare

import fr.esgi.trainstation.activityDetailGare.ApiResponse.AccompagnementPMRResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareEquipeeWifiResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.RepartitionHFResponse
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

    suspend fun getHorairesFromNomGare(nomGare: String): HoraireGareResponse {
        return client.get<HoraireGareResponse>("https://ressources.data.sncf.com/api/explore/v2.1/catalog/datasets/horaires-des-gares1/records?limit=20&refine=nom_normal:$nomGare")
    }

    suspend fun getAccompagnmentPMRFromNomGare(nomGare: String): AccompagnementPMRResponse {
        return client.get<AccompagnementPMRResponse>("https://ressources.data.sncf.com/api/explore/v2.1/catalog/datasets/accompagnement-pmr-gares/records?limit=20&refine=gare:$nomGare")
    }

    suspend fun getRepartitionHFFromNomGare(nomGare: String): RepartitionHFResponse {
        return client.get<RepartitionHFResponse>("https://ressources.data.sncf.com/api/explore/v2.1/catalog/datasets/enquetes-gares-connexions-repartition-homme-femme/records?limit=20&refine=gare_enquetee:$nomGare")
    }

    suspend fun getGareEquipeeWifiFromNomGare(nomGare: String): GareEquipeeWifiResponse {
        val finalNomGare = nomGare.replace(" ", "-")
        return client.get<GareEquipeeWifiResponse>("https://ressources.data.sncf.com/api/explore/v2.1/catalog/datasets/gares-equipees-du-wifi/records?limit=20&refine=nom_de_la_gare:$finalNomGare")
    }
}