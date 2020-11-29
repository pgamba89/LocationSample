package com.example.meepappsample.model

data class ResponseModel(
    val id: String? = null,
    val name: String? = null,
    val x: Double? = null,
    val y: Double? = null,
    val scheduledArrival: Long? = null,
    val locationType: Long? = null,
    val companyZoneId: Int? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val licencePlate: String? = null,
    val range: Long? = null,
    val batteryLevel: Long? = null,
    val helmets: Long? = null,
    val model: Model? = null,
    val resourceImageID: ResourceImageID? = null,
    val realTimeData: Boolean? = null,
    val resourceType: ResourceType? = null,
    val station: Boolean? = null,
    val availableResources: Long? = null,
    val spacesAvailable: Long? = null,
    val allowDropoff: Boolean? = null,
    val bikesAvailable: Long? = null
)

enum class Model {
}

enum class ResourceImageID {
    VehicleGenEcooltra
}

enum class ResourceType {
    Moped
}