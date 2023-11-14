package javajedi.com.model

import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse(
    val message: String
)