package yuque2mk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    var id = 0

    var type = ""

    @get:JsonProperty("login")
    var loginName = ""

    var name = ""

    var description: String? = null

    @get:JsonProperty("avatar_url")
    var avatarUrl = ""

    @get:JsonProperty("created_at")
    var createdAt = ""

    @get:JsonProperty("updated_at")
    var updatedAt = ""
}