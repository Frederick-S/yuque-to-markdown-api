package yuque2md.controller.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import yuque2md.annotation.AccessToken
import yuque2md.annotation.LoginRequired
import yuque2md.dto.User
import yuque2md.service.YuqueService

@RestController
class UserController : BaseApiController() {
    @Autowired
    private lateinit var yuqueService: YuqueService

    @LoginRequired
    @GetMapping("/me")
    fun getMe(@AccessToken accessToken: String): User {
        return yuqueService.getMe(accessToken)
    }
}
