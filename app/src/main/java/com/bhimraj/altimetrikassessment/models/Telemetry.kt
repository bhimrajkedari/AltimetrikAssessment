package com.bhimraj.altimetrikassessment.models


import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: Any
)