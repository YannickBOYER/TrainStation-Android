package fr.esgi.trainstation.activityDetailGare.ApiResponse

import kotlinx.serialization.Serializable

@Serializable
data class Assistance(
    val datemensuel: String,
    val uic: String,
    val gare: String,
    val assistance_simple: Int,
    val rampe: Int,
    val fauteuil: Int,
    val rampe_fauteuil: Int,
    val total: Int
)

@Serializable
data class AccompagnementPMRResponse(
    val total_count: Int,
    val results: List<Assistance>
)
