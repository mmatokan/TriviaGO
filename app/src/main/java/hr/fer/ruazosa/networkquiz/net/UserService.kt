package hr.fer.ruazosa.networkquiz.net

import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.http.*
import retrofit2.Call

interface  UserService {

    @POST("/registerUser")
    fun registerUser(@Body user: User): User?

    @POST("/loginUser")
    fun loginUser(@Body user: ShortUser): User?

    @GET("/userRank/{id}")
    fun getUserRank(@Path("id") username: String): Int?

    @GET("/usernames")
    fun getOpponents(@Query("usernameToExclude") usernameToExclude:String) : List<String>

    @GET("/token/{id}")
    fun getUserToken(@Path("id") username: String): String?

    @PATCH("/token/{id}")
    @FormUrlEncoded
    fun setNewToken(@Path("id") username: String, @Field("token") newToken: String): String?
}