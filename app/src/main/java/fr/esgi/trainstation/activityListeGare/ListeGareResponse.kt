package fr.esgi.trainstation.activityListeGare

import kotlinx.serialization.Serializable

@Serializable
data class ListeGareResponse(
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)

@Serializable
data class Parameters(
    val dataset: List<String>,
    val rows: Int,
    val start: Int,
    val format: String,
    val timezone: String,
)

@Serializable
data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

@Serializable
data class Fields(
    val idreseau: Int,
    val y_l93: Double,
    val commune: String,
    val voyageurs: String,
    val code_uic: String,
    val geo_shape: Geometry,
    val fret: String,
    val libelle: String,
    val pk: String,
    val idgaia: String,
    val departemen: String,
    val rg_troncon: Int,
    val x_l93: Double,
    val x_wgs84: Double,
    val code_ligne: String,
    val c_geo: List<Double>,
    val geo_point_2d: List<Double>,
    val y_wgs84: Double,
    val dist: String
)

@Serializable
data class Record(
    val datasetid: String,
    val recordid: String,
    val fields: Fields,
    val geometry: Geometry,
    val record_timestamp: String
)