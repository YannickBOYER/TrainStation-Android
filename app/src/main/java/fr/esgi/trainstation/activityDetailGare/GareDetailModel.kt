package fr.esgi.trainstation.activityDetailGare

import fr.esgi.trainstation.activityDetailGare.ApiResponse.AccompagnementPMRResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareEquipeeWifiResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.HoraireGareResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.RepartitionHFResponse

data class GareDetailModel (
    val nom: String,
    val horaires: HoraireGareResponse,
    val accompagnementPMRResponse: AccompagnementPMRResponse,
    val repartitionHFResponse: RepartitionHFResponse,
    val gareEquipeeWifiResponse: GareEquipeeWifiResponse
)