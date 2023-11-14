package javajedi.com.request

import kotlinx.serialization.Serializable

@Serializable
data class CustomerRequest(
    val name: String
)