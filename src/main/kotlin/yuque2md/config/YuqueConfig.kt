package yuque2md.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "yuque")
class YuqueConfig {
    var clientId = ""

    var clientSecret = ""

    var authorizationGrantType = ""

    var redirectUri = ""

    var clientRedirectUri = ""

    var scope = ""

    var authorizationUri = ""

    var tokenUri = ""
}
