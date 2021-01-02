package hr.fer.ruazosa.networkquiz.net

import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit2.Call

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: ShortUser): User?
    fun getUserRank(username: String): Int?
}