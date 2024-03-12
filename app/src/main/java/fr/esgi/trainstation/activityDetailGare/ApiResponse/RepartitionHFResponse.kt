package fr.esgi.trainstation.activityDetailGare.ApiResponse

import kotlinx.serialization.Serializable

@Serializable
data class EnqueteResult(
    val uic: String,
    val gare_enquetee: String,
    val sexe: String,
    val pourcentage: Int,
    val annee: String
)

@Serializable
data class RepartitionHFResponse(
    val total_count: Int,
    val results: List<EnqueteResult>
)
