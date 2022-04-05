package yuque2md.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.*
import org.springframework.stereotype.Service
import yuque2md.dto.*
import java.io.IOException

@Service
class YuqueService : AbstractYuqueService() {
    fun getMe(accessToken: String): User {
        val url = "$baseUrl/user"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()

        if (response.code != 200) {
            throw IOException("api request error")
        }

        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), User::class.java)
    }

    fun getRepos(userId: Long, accessToken: String): List<Repo> {
        val url = "$baseUrl/users/$userId/repos"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()

        if (response.code != 200) {
            throw IOException("api request error")
        }

        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), object : TypeReference<List<Repo>>() {})
    }

    fun getRepoDetail(repoId: Long, accessToken: String): RepoDetail {
        val url = "$baseUrl/repos/${repoId}"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()

        if (response.code != 200) {
            throw IOException("api request error")
        }

        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), RepoDetail::class.java)
    }

    /**
     * offset starts from 0
     */
    fun getDocs(repoId: Long, offset: Int?, limit: Int?, accessToken: String): List<Doc> {
        val url = "$baseUrl/repos/$repoId/docs?offset=$offset&limit=$limit"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()

        if (response.code != 200) {
            throw IOException("api request error")
        }

        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), object : TypeReference<List<Doc>>() {})
    }

    fun getDocDetail(repoId: Long, docId: Long, accessToken: String): DocDetail {
        val url = "$baseUrl/repos/$repoId/docs/$docId?raw=1"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()

        if (response.code != 200) {
            throw IOException("api request error")
        }

        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), DocDetail::class.java)
    }

    fun getDocDetailAsync(repoId: Long, docId: Long, accessToken: String, channel: Channel<DocDetail>) {
        val url = "$baseUrl/repos/$repoId/docs/$docId?raw=1"
        val request = Request.Builder()
            .url(url)
            .headers(getCommonHeaders(accessToken))
            .build()
        OkHttpClient().newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    GlobalScope.launch {
                        channel.send(DocDetail())
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.code != 200) {
                        GlobalScope.launch {
                            channel.send(DocDetail())
                        }

                        return
                    }

                    val json = response.body?.string()
                    val objectMapper = ObjectMapper()
                    val jsonNode = objectMapper.readTree(json)
                    val docDetail = objectMapper.convertValue(jsonNode.get("data"), DocDetail::class.java)

                    GlobalScope.launch {
                        channel.send(docDetail)
                    }
                }
            })
    }
}
