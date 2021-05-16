package yuque2mk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class DocDetail {
    var id: Long = 0

    var slug = ""

    var title = ""

    @get:JsonProperty("repoId")
    @set:JsonProperty("book_id")
    var repoId: Long = 0

    @get:JsonProperty("userId")
    @set:JsonProperty("user_id")
    var userId: Long = 0

    var format = ""

    var body = ""

    @get:JsonProperty("bodyDraft")
    @set:JsonProperty("body_draft")
    var bodyDraft = ""

    @get:JsonProperty("bodyHtml")
    @set:JsonProperty("body_html")
    var bodyHtml = ""

    @get:JsonProperty("bodyLake")
    @set:JsonProperty("body_lake")
    var bodyLake = ""

    @get:JsonProperty("creatorId")
    @set:JsonProperty("creator_id")
    var creatorId: Long = 0

    var public = 0

    var status = 0

    @get:JsonProperty("contentUpdatedAt")
    @set:JsonProperty("content_updated_at")
    var contentUpdatedAt = ""

    @get:JsonProperty("deletedAt")
    @set:JsonProperty("deleted_at")
    var deletedAt = null

    @get:JsonProperty("createdAt")
    @set:JsonProperty("created_at")
    var createdAt = ""

    @get:JsonProperty("updatedAt")
    @set:JsonProperty("updated_at")
    var updatedAt = ""
}