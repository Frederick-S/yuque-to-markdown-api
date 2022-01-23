package yuque2md

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import yuque2md.interceptor.AuthenticationInterceptor
import yuque2md.support.AccessTokenHandlerMethodArgumentResolver

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Autowired
    private lateinit var authenticationInterceptor: AuthenticationInterceptor

    @Autowired
    private lateinit var accessTokenHandlerMethodArgumentResolver: AccessTokenHandlerMethodArgumentResolver

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addInterceptor(authenticationInterceptor)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)

        resolvers.add(accessTokenHandlerMethodArgumentResolver)
    }
}
