package yuque2md.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import redis.clients.jedis.JedisPool
import redis.clients.jedis.params.SetParams
import yuque2md.config.RedisConfig

@Service
class CacheService @Autowired constructor(@Autowired redisConfig: RedisConfig) {
    private var pool = JedisPool(redisConfig.host, redisConfig.port)

    fun get(key: String): String {
        pool.resource.use {
            return it.get(key)
        }
    }

    fun set(key: String, value: String, secondsToExpire: Long) {
        val setParams = SetParams()
        setParams.ex(secondsToExpire)

        pool.resource.use {
            it.set(key, value, setParams)
        }
    }
}