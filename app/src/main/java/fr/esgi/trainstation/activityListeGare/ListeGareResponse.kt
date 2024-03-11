package fr.esgi.trainstation.activityListeGare

import kotlinx.serialization.Serializable

@Serializable
data class Parameters(
    val dataset: String,
    val rows: Int,
    val start: Int,
    val format: String,
    val timezone: String
)

@Serializable
data class Fields(
    val nom: String,
    val position_geographique: List<Double>,
    val segment_drg: String,
    val codeinsee: String,
    val libellecourt: String,
    val dist: String
)

@Serializable
data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

@Serializable
data class Record(
    val datasetid: String,
    val recordid: String,
    val fields: Fields,
    val geometry: Geometry,
    val record_timestamp: String
)

@Serializable
data class ListeGareResponse(
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)
