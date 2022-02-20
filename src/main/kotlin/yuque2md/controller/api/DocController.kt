package yuque2md.controller.api

import kotlinx.coroutines.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import yuque2md.annotation.AccessToken
import yuque2md.annotation.LoginRequired
import yuque2md.dto.Doc
import yuque2md.dto.DocDetail
import yuque2md.service.YuqueService
import java.util.concurrent.ConcurrentLinkedQueue

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
    fun getDocDetail(@PathVariable repoId: Long, @PathVariable docId: Long, @AccessToken accessToken: String): DocDetail {
        return yuqueService.getDocDetail(repoId, docId, accessToken)
    }

    @LoginRequired
    @PostMapping("/repos/{repoId}/docs/export")
    fun export(@PathVariable repoId: Long, @AccessToken accessToken: String): String {
        val docs = yuqueService.getDocs(repoId, null, null, accessToken)
        val docDetails = ConcurrentLinkedQueue<DocDetail>()
        val tasks = docs.map {
            GlobalScope.launch {
                val docDetail = yuqueService.getDocDetail(repoId, it.id, accessToken)
                docDetails.add(docDetail)
            }
        }

        runBlocking { tasks.joinAll() }

        return ""
    }
}
