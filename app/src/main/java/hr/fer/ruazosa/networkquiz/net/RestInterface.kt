package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.*
import java.util.*

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: ShortUser): User?
    fun getUserRank(username: String): User?
    fun getCategories(count: Int): List<Category>?
    fun getOpponents(usernameToExclude: String) : List<User>?
    fun getUserToken(username: String): String?
    fun setNewToken(username: String, token: String): String?
    fun getQuestions(categoryId: Int): CatQuestions?


}