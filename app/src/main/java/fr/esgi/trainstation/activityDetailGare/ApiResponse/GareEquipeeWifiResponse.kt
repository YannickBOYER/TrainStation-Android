package fr.esgi.trainstation.activityDetailGare.ApiResponse

import kotlinx.serialization.Serializable

@Serializable
data class WifiService(
    val uic: String,
    val nom_de_la_gare: String,
    val service_wifi: String
)

@Serializable
data class GareEquipeeWifiResponse(
    val total_count: Int,
    val results: List<WifiService>
)