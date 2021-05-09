package yuque2mk.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class YuqueTokenResponse {
    @set:JsonProperty("access_token")
    var accessToken = ""

    @set:JsonProperty("token_type")
    var tokenType = ""

    var scope = ""
}