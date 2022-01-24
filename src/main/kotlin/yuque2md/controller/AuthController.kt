package yuque2md.controller

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import yuque2md.config.YuqueConfig
import yuque2md.dto.YuqueTokenResponse
import yuque2md.service.CacheService
import java.util.*
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("oauth2")
class AuthController {
    @Autowired
    private lateinit var yuqueConfig: YuqueConfig

    @Autowired
    private lateinit var cacheService: CacheService

    @GetMapping("/authorize")
    fun authorize(): String {
        val url =
            "${yuqueConfig.authorizationUri}?client_id=${yuqueConfig.clientId}&scope=${yuqueConfig.scope}&redirect_uri=${yuqueConfig.redirectUri}&response_type=code"

        return "redirect:$url"
    }

    @GetMapping("/authorized")
    fun authorized(@RequestParam code: String, @RequestParam state: String, httpSession: HttpSession): String {
        val requestBody = FormBody.Builder()
            .add("client_id", yuqueConfig.clientId)
            .add("client_secret", yuqueConfig.clientSecret)
            .add("code", code)
            .add("grant_type", yuqueConfig.authorizationGrantType)
            .build()
        val request = Request.Builder()
            .url(yuqueConfig.tokenUri)
            .post(requestBody)
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val token = objectMapper.readValue(json, YuqueTokenResponse::class.java)
        val uuid = UUID.randomUUID().toString()

        cacheService.set(uuid, token.accessToken, 60 * 60 * 24)

        return "redirect:http://localhost:8000/?tokenId=${uuid}"
    }
}
