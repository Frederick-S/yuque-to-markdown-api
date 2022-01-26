package yuque2md.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import yuque2md.annotation.LoginRequired
import yuque2md.service.CacheService
import javax.servlet.http.HttpServletRequest

@CrossOrigin
@RestController
class LogoutController {
    @Autowired
    private lateinit var cacheService: CacheService

    @LoginRequired
    @PostMapping("/logout")
    fun logout(httpServletRequest: HttpServletRequest): String {
        val tokenId = httpServletRequest.getHeader("X-tokenId")

        if (tokenId != null) {
            cacheService.delete(tokenId)
        }

        return "ok"
    }
}