package javajedi.data

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsUserRatings(
    val name: String,
    val director: String,
    val userRatings: List<UserRating>
)

@Serializable
data class UserRating(
    val name: String,
    val value: Short
)