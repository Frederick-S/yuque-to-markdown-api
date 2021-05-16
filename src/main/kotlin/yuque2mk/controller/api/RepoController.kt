package yuque2mk.controller.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import yuque2mk.YuqueService
import yuque2mk.dto.Repo
import javax.servlet.http.HttpSession

@RestController
class RepoController {
    @Autowired
    lateinit var yuqueService: YuqueService

    @GetMapping("/users/{userId}/repos")
    fun getRepos(@PathVariable userId: Long, httpSession: HttpSession): List<Repo> {
        return yuqueService.getRepos(userId, httpSession.getAttribute("accessToken").toString())
    }
}
