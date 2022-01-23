package yuque2md.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import yuque2md.annotation.LoginRequired
import yuque2md.service.CacheService
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor : HandlerInterceptor {
    private val objectMapper = ObjectMapper()

    @Autowired
    private lateinit var cacheService: CacheService

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }

        if (!handler.method.isAnnotationPresent(LoginRequired::class.java)) {
            return true
        }

        val uuid = request.getHeader("X-tokenId")
        val accessToken = uuid?.let {
            cacheService.get(it)
        }

        if (accessToken == null || accessToken.isEmpty()) {
            sendUnauthorizedError(response)

            return false
        }

        return true
    }

    private fun sendUnauthorizedError(response: HttpServletResponse) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON.toString()
    }
}
