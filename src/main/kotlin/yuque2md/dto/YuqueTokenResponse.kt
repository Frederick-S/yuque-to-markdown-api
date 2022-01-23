package yuque2md.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class YuqueTokenResponse {
    @get:JsonProperty("accessToken")
    @set:JsonProperty("access_token")
    var accessToken = ""

    @get:JsonProperty("tokenType")
    @set:JsonProperty("token_type")
    var tokenType = ""

    var scope = ""
}
