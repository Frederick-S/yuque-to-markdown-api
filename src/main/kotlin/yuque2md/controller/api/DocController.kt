package yuque2md.controller.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yuque2md.annotation.AccessToken
import yuque2md.annotation.LoginRequired
import yuque2md.dto.Doc
import yuque2md.dto.DocDetail
import yuque2md.service.YuqueService

@RestController
class DocController : BaseApiController() {
    @Autowired
    private lateinit var yuqueService: YuqueService

    @LoginRequired
    @GetMapping("/repos/{repoId}/docs")
    fun getDocs(
        @PathVariable repoId: Long,
        @RequestParam(required = false) offset: Int?,
        @RequestParam(required = false) limit: Int?,
        @AccessToken accessToken: String
    ): List<Doc> {
        return yuqueService.getDocs(repoId, offset, limit, accessToken)
    }

    @LoginRequired
    @GetMapping("/repos/{repoId}/docs/{docId}")
    fun getDocDetail(@PathVariable repoId: Long, @PathVariable docId: String, @AccessToken accessToken: String): DocDetail {
        return yuqueService.getDocDetail(repoId, docId, accessToken)
    }
}
