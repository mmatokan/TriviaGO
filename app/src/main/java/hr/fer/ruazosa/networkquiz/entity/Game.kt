package hr.fer.ruazosa.networkquiz.entity

import java.io.Serializable

data class Game (
    var id: Int,
    var questions : MutableList<Question>,
    var players : MutableList<String>,
    var pending : Int
):Serializable