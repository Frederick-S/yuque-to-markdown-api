package yuque2mk.controller.api

import javax.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import yuque2mk.annotation.LoginRequired
import yuque2mk.service.YuqueService
import yuque2mk.dto.Repo

@RestController
class RepoController : BaseApiController() {
    @Autowired
    private lateinit var yuqueService: YuqueService

    @LoginRequired
    @GetMapping("/users/{userId}/repos")
    fun getRepos(@PathVariable userId: Long, httpSession: HttpSession): List<Repo> {
        return yuqueService.getRepos(userId, httpSession.getAttribute("accessToken").toString())
    }
}
