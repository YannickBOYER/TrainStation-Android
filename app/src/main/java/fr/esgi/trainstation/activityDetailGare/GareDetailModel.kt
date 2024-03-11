package fr.esgi.trainstation.activityDetailGare

import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse

data class GareDetailModel (
    val nom: String,
    val horaires: HoraireGareResponse
)