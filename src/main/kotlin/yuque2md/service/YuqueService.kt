package yuque2md.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import yuque2md.dto.Doc
import yuque2md.dto.DocDetail
import yuque2md.dto.Repo
import yuque2md.dto.User

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
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), object : TypeReference<List<Repo>>() {})
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
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), DocDetail::class.java)
    }
}
