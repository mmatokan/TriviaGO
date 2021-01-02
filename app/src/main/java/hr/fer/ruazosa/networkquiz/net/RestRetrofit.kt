package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.RestAdapter

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
}