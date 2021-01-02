package hr.fer.ruazosa.networkquiz.net

import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.http.POST
import retrofit.http.GET
import retrofit.http.Body
import retrofit.http.Path
import retrofit2.Call

interface UserService {

    @POST("/registerUser")
    fun registerUser(@Body user: User): User?

    @POST("/loginUser")
    fun loginUser(@Body user: ShortUser): User?

    @GET("/userRank/{id}")
    fun getUserRank(@Path("id") username: String): Int?

}