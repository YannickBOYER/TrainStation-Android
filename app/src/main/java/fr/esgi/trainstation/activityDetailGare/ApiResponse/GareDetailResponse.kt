package fr.esgi.trainstation.activityDetailGare.ApiResponse

import kotlinx.serialization.Serializable

@Serializable
data class PositionGeographique(
    val lon: Double,
    val lat: Double
)

@Serializable
data class GareInfo(
    val nom: String,
    val libellecourt: String,
    val segment_drg: String,
    val position_geographique: PositionGeographique,
    val codeinsee: String
)

@Serializable
data class GareDetailResponse(
    val total_count: Int,
    val results: List<GareInfo>
)
