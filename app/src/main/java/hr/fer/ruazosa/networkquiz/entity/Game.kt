package hr.fer.ruazosa.networkquiz.entity

data class Game (
    var questions : MutableList<Question>,
    var players : MutableList<String>,
    var pending : Int
)