package hr.fer.ruazosa.networkquiz.net

import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User

interface RestInterface {
    fun registerUser(user: User): User?
    fun loginUser(user: ShortUser): User?
}