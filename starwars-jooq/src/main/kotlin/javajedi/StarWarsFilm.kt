package javajedi

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsFilm(
    val sequelId: Short,
    val name: String,
    val director: String,
)