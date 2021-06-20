package yuque2mk.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import yuque2mk.annotation.LoginRequired
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationInterceptor : HandlerInterceptor {
    private val objectMapper = ObjectMapper()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }

        if (!handler.method.isAnnotationPresent(LoginRequired::class.java)) {
            return true
        }

        val accessToken = request.session.getAttribute("accessToken") as String?

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