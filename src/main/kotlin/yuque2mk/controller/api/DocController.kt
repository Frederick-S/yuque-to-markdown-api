package yuque2mk.controller.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yuque2mk.YuqueService
import yuque2mk.dto.Doc
import javax.servlet.http.HttpSession

@RestController
class DocController {
    @Autowired
    lateinit var yuqueService: YuqueService

    @GetMapping("/docs")
    fun getDocs(@RequestParam repoNamespace: String, httpSession: HttpSession): List<Doc> {
        return yuqueService.getDocs(repoNamespace, httpSession.getAttribute("accessToken").toString())
    }
}