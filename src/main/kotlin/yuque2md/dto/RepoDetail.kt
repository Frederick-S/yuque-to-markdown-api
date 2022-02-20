package yuque2md.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class RepoDetail {
    var id = 0

    var type = ""

    var slug = ""

    var name = ""

    var namespace = ""

    @get:JsonProperty("userId")
    @set:JsonProperty("user_id")
    var userId = 0

    var description = ""

    @get:JsonProperty("tocYml")
    @set:JsonProperty("toc_yml")
    var tocYml = ""

    @get:JsonProperty("creatorId")
    @set:JsonProperty("creator_id")
    var creatorId = 0

    var public = 0

    @get:JsonProperty("itemsCount")
    @set:JsonProperty("items_count")
    var itemsCount = 0

    @get:JsonProperty("likesCount")
    @set:JsonProperty("likes_count")
    var likesCount = 0

    @get:JsonProperty("watchesCount")
    @set:JsonProperty("watches_count")
    var watchesCount = 0

    @get:JsonProperty("createdAt")
    @set:JsonProperty("created_at")
    var createdAt = ""

    @get:JsonProperty("updatedAt")
    @set:JsonProperty("updated_at")
    var updatedAt = ""
}