package hr.fer.ruazosa.networkquiz.net

import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.*
import retrofit.http.*

interface  UserService {

    @POST("/registerUser")
    fun registerUser(@Body user: User): User?

    @POST("/loginUser")
    fun loginUser(@Body user: ShortUser): User?

    @GET("/userRank/{id}")
    fun getUserRank(@Path("id") username: String): User?

    @GET("/usernames")
    fun getOpponents(@Query("usernameToExclude") usernameToExclude:String) : List<User>

    @GET("/token/{id}")
    fun getUserToken(@Path("id") username: String): String?

    @PATCH("/token/{id}")
    @FormUrlEncoded
    fun setNewToken(@Path("id") username: String, @Field("token") newToken: String): String?

    @GET("questions/{id}")
    fun getQuestions(@Path("id") categoryId: Int): CatQuestions?

    @POST("/createNewGame")
    fun createNewGame(@Body game: Game): Game?

    @PATCH("/joinGame/{id}")
    fun joinGameResponse(@Path("id") gameId: Int, @Query("response") response: Boolean, @Body player: User): Game?

}