package fr.esgi.trainstation.activityDetailGare.ApiResponse

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val uic: String,
    val nom_normal: String,
    val jour: String,
    val horaire_normal: String,
    val horaire_ferie: String?
)

@Serializable
data class HoraireGareResponse(
    val total_count: Int,
    val results: List<Result>
)
