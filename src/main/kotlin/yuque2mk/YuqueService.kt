package yuque2mk

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import yuque2mk.dto.Doc
import yuque2mk.dto.Repo
import yuque2mk.dto.User

@Service
class YuqueService {
    val baseUrl = "https://www.yuque.com/api/v2"

    fun getMe(accessToken: String): User {
        val url = "${baseUrl}/user"
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "yuque-2-markdown")
            .header("Content-Type", "application/json")
            .header("X-Auth-Token", accessToken)
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), User::class.java)
    }

    fun getRepos(userId: Long, accessToken: String): List<Repo> {
        val url = "${baseUrl}/users/${userId}/repos"
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "yuque-2-markdown")
            .header("Content-Type", "application/json")
            .header("X-Auth-Token", accessToken)
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), object : TypeReference<List<Repo>>() {})
    }

    fun getDocs(repoNamespace: String, accessToken: String): List<Doc> {
        val url = "${baseUrl}/repos/${repoNamespace}/docs"
        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "yuque-2-markdown")
            .header("Content-Type", "application/json")
            .header("X-Auth-Token", accessToken)
            .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()
        val json = response.body?.string()
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(json)

        return objectMapper.convertValue(jsonNode.get("data"), object : TypeReference<List<Doc>>() {})
    }
}