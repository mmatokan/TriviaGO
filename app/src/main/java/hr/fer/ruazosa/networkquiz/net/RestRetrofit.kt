package hr.fer.ruazosa.networkquiz.net

import android.util.Log
import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.RestAdapter
import retrofit2.Call

class RestRetrofit : RestInterface{

    private val service: UserService

    init{
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080/"
        val retrofit = RestAdapter.Builder().setEndpoint(baseURL).build()

        service = retrofit.create(UserService::class.java)
    }


    override fun registerUser(user: User): User? {
        return service.registerUser(user)
    }

    override fun loginUser(user: ShortUser): User? {
        return service.loginUser(user)
    }

    override fun getUserRank(username: String): Int? {
        return service.getUserRank(username)
    }
}