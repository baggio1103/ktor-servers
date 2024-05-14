package javajedi.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUser(
    val login: String,
    val password: String
)


data class LoginUser(
    val login: String,
    val password: String
)