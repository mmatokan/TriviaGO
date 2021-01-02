package hr.fer.ruazosa.networkquiz.net

import android.util.Log
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.util.LinkedMultiValueMap


class RestSpringTemplate: RestInterface {
    private var baseURL: String = "http://" + RestFactory.BASE_IP + ":8080/"

    private val restTemplate = RestTemplate()

    init{
        restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())
    }

    override fun registerUser(user: User): User? {
        try {
            val headers = LinkedMultiValueMap<String, String>()
            headers.add("HeaderName", "value")
            //headers.add("Content-Type", "application/json")
            val restTemplate = RestTemplate()
            restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())

            val request = HttpEntity<User>(user, headers)

            return restTemplate.postForObject("${baseURL}/registerUser", request, User::class.java)
        } catch (e: Exception) {
            Log.i("RUAZOSA", e.message, e)
        }

        return null
    }

    override fun loginUser(user: ShortUser): User? {
        try {
            val headers = LinkedMultiValueMap<String, String>()
            headers.add("HeaderName", "value")
            //headers.add("Content-Type", "application/json")
            val restTemplate = RestTemplate()
            restTemplate.messageConverters.add(MappingJackson2HttpMessageConverter())

            val request = HttpEntity<ShortUser>(user, headers)

            return restTemplate.postForObject("${baseURL}/loginUser", request, User::class.java)
        } catch (e: Exception) {
            Log.i("RUAZOSA", e.message, e)
        }

        return null
    }
}