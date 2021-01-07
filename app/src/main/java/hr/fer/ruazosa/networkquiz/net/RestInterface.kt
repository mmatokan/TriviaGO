package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.Category
import hr.fer.ruazosa.networkquiz.entity.User

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: ShortUser): User?
    fun getUserRank(username: String): Int?
    fun getCategories(count: Int): List<Category>?
    fun getOpponents(usernameToExclude: String) : List<String>?
    fun getUserToken(username: String): String?
    fun setNewToken(username: String, token: String): String?

}