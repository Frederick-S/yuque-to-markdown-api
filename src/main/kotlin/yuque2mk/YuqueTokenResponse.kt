package yuque2mk

import com.fasterxml.jackson.annotation.JsonProperty

class YuqueTokenResponse {
    @JsonProperty("access_token")
    var accessToken = ""

    @JsonProperty("token_type")
    var tokenType = ""

    var scope = ""
}