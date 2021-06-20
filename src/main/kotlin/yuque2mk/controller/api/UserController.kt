package yuque2mk.controller.api

import javax.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import yuque2mk.annotation.LoginRequired
import yuque2mk.service.YuqueService
import yuque2mk.dto.User

@RestController
class UserController : BaseApiController() {
    @Autowired
    private lateinit var yuqueService: YuqueService

    @LoginRequired
    @GetMapping("/me")
    fun getMe(httpSession: HttpSession): User {
        return yuqueService.getMe(httpSession.getAttribute("accessToken").toString())
    }
}
