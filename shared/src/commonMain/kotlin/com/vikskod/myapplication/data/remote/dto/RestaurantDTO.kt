package com.vikskod.myapplication.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Data transfer object for the restaurant details
// Using just the required fields from the payload

@Serializable
data class RestaurantDTO(
    @SerialName("restaurants")
    val restaurants: List<Restaurant>,
    @SerialName("results_shown")
    val resultsShown: Int
)

@Serializable
data class Restaurant(
    @SerialName("restaurant")
    val restaurant: RestaurantX
)

@Serializable
data class RestaurantX(
    @SerialName("featured_image")
    val featuredImage: String,
    @SerialName("id")
    val id: String,
    @SerialName("location")
    val location: Location,
    @SerialName("name")
    val name: String
)

@Serializable
data class Location(
    @SerialName("address")
    val address: String
)