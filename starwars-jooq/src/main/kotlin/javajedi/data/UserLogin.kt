package javajedi.data

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(
    val username: String,
    val password: String
)