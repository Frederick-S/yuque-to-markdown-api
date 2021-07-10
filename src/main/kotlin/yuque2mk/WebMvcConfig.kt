package yuque2mk

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import yuque2mk.interceptor.AuthenticationInterceptor
import yuque2mk.support.AccessTokenHandlerMethodArgumentResolver

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)

        registry.addInterceptor(AuthenticationInterceptor())
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        super.addArgumentResolvers(resolvers)

        resolvers.add(AccessTokenHandlerMethodArgumentResolver())
    }
}
