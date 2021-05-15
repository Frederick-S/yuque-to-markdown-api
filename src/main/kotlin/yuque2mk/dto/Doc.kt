package yuque2mk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Doc {
    var id: Long = 0

    var title = ""

    var slug = ""

    var status = 0

    var public = 0

    @get:JsonProperty("createdAt")
    @set:JsonProperty("created_at")
    var createdAt = ""

    @get:JsonProperty("updatedAt")
    @set:JsonProperty("updated_at")
    var updatedAt = ""
}