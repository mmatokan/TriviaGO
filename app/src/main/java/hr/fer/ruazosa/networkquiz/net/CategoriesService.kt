package hr.fer.ruazosa.networkquiz.net
import hr.fer.ruazosa.networkquiz.entity.Category
import retrofit.http.GET
import retrofit.http.Path

interface CategoriesService {

    @get:GET("/categories?count=100")
    val listOfCategories: List<Category>?
}