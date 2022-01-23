package yuque2md.service

import okhttp3.Headers

abstract class AbstractYuqueService {
    protected val baseUrl = "https://www.yuque.com/api/v2"

    protected fun getCommonHeaders(accessToken: String): Headers {
        return Headers.Builder()
            .add("User-Agent", "yuque-2-markdown")
            .add("Content-Type", "application/json")
            .add("X-Auth-Token", accessToken)
            .build()
    }
}
