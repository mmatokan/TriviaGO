package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.Category
import hr.fer.ruazosa.networkquiz.entity.Question
import hr.fer.ruazosa.networkquiz.entity.User
import retrofit.RestAdapter
import java.util.*

class RestRetrofit : RestInterface{

    private val service: UserService
    private val questionService: CategoriesService

    init{
        val baseURL = "http://" + RestFactory.BASE_IP + ":8080/"
        val retrofit = RestAdapter.Builder().setEndpoint(baseURL).build()
        val questionULR = "http://jservice.io/api"
        val questionRetrofit = RestAdapter.Builder().setEndpoint(questionULR).build()

        service = retrofit.create(UserService::class.java)
        questionService = questionRetrofit.create(CategoriesService::class.java)
    }

    override fun registerUser(user: User): User? {
        return service.registerUser(user)
    }

    override fun loginUser(user: ShortUser): User? {
        return service.loginUser(user)
    }

    override fun getUserRank(username: String): User? {
        return service.getUserRank(username)
    }

    override fun getCategories(count: Int): List<Category>?{
        return questionService.listOfCategories
    }

    override fun getOpponents(usernameToExclude:String): List<User>? {
        return service.getOpponents(usernameToExclude)
    }

    override fun getUserToken(username: String): String? {
        return service.getUserToken(username)
    }

    override fun setNewToken(username: String, token: String): String? {
       return service.setNewToken(username, token)
    }

    override fun getQuestions(categoryId: Int): List<Question>? {
        return service.getQuestions(categoryId)
    }
}