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
    var accuracy: Int = 0
    var gamesPlayed: Int = 0
    var score: Int = 0
    var rank: Int = 0
}