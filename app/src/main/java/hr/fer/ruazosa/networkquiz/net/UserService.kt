package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.http.POST
import retrofit.http.Body

interface UserService {

    @POST("/registerUser")
    fun registerUser(@Body user: User): User?

    @POST("/loginUser")
    fun loginUser(@Body user: ShortUser): User?

}