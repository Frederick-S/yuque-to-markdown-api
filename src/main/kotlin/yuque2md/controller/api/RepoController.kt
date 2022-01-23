package yuque2md.controller.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import yuque2md.annotation.AccessToken
import yuque2md.annotation.LoginRequired
import yuque2md.dto.Repo
import yuque2md.service.YuqueService

@RestController
class RepoController : BaseApiController() {
    @Autowired
    private lateinit var yuqueService: YuqueService

    @LoginRequired
    @GetMapping("/users/{userId}/repos")
    fun getRepos(@PathVariable userId: Long, @AccessToken accessToken: String): List<Repo> {
        return yuqueService.getRepos(userId, accessToken)
    }
}
