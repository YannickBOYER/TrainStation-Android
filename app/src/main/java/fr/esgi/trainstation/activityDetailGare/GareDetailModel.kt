package fr.esgi.trainstation.activityDetailGare

import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareDetailResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.GareEquipeeWifiResponse
import fr.esgi.trainstation.activityDetailGare.ApiResponse.RepartitionHFResponse

data class GareInformationGeneraleModel(
    val details: GareDetailResponse,
    val gareEquipeeWifiResponse: GareEquipeeWifiResponse,
    val repartitionHFResponse: RepartitionHFResponse
)