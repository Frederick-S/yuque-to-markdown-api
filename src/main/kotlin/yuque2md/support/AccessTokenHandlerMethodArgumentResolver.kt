package yuque2md.support

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import yuque2md.annotation.AccessToken
import yuque2md.service.CacheService
import javax.servlet.http.HttpServletRequest

@Component
class AccessTokenHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {
    @Autowired
    private lateinit var cacheService: CacheService

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(AccessToken::class.java) != null
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val request = webRequest.nativeRequest as HttpServletRequest
        val uuid = request.getHeader("X-tokenId")

        return cacheService.get(uuid)
    }
}
