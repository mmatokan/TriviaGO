package hr.fer.ruazosa.networkquiz.entity

import java.io.Serializable

data class User (
    var firstName : String,
    var lastName : String,
    var username : String,
    var email : String,
    var password : String,
    var token : String
) : Serializable{
    var id: Int = 0
    var accuracy: Int = 1
    var gamesPlayed: Int = 2
    var score: Int = 3
    var rank: Int = 0
}