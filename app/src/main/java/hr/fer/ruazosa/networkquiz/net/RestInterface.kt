package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.Category
import hr.fer.ruazosa.networkquiz.entity.Question
import hr.fer.ruazosa.networkquiz.entity.User
import java.util.*

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: ShortUser): User?
    fun getUserRank(username: String): User?
    fun getCategories(count: Int): List<Category>?
    fun getOpponents(usernameToExclude: String) : List<User>?
    fun getUserToken(username: String): String?
    fun setNewToken(username: String, token: String): String?
    fun getQuestions(categoryId: Int): List<Question>?

}