package yuque2md.controller.api

import com.google.common.collect.Lists
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Semaphore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import yuque2md.annotation.AccessToken
import yuque2md.annotation.LoginRequired
import yuque2md.dto.Doc
import yuque2md.dto.DocDetail
import yuque2md.service.YuqueService
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.math.ceil

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
    fun getDocDetail(
        @PathVariable repoId: Long,
        @PathVariable docId: Long,
        @AccessToken accessToken: String
    ): DocDetail {
        return yuqueService.getDocDetail(repoId, docId, accessToken)
    }

    @LoginRequired
    @PostMapping("/repos/{repoId}/docs/export")
    fun export(@PathVariable repoId: Long, @AccessToken accessToken: String): String {
        val repoDetail = yuqueService.getRepoDetail(repoId, accessToken)
        val count = repoDetail.itemsCount
        val limit = 500
        val docs = IntRange(0, ceil(count / limit.toFloat()).toInt())
            .map {
                yuqueService.getDocs(repoId, it * limit, limit, accessToken)
            }
            .flatten()
        val docDetails = ConcurrentLinkedQueue<DocDetail>()
        val semaphore = Semaphore(1)
        val channel = Channel<DocDetail>()
        val concurrentRequests = 50

        val tasks = Lists.partition(docs, concurrentRequests)
            .map {
                GlobalScope.launch {
                    semaphore.acquire()

                    it.forEach {
                        yuqueService.getDocDetailAsync(repoId, it.id, accessToken, channel)
                    }

                    repeat(it.size) {
                        val docDetail = channel.receive()

                        if (docDetail.id > 0) {
                            docDetails.add(docDetail)
                        }
                    }

                    semaphore.release()
                }
            }

        runBlocking {
            tasks.joinAll()
        }

        return ""
    }
}
