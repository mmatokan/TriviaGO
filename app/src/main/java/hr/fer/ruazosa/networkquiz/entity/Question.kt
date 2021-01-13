package hr.fer.ruazosa.networkquiz.entity

import java.io.Serializable
data class Question (

    var id: Int,
    var answer: String,
    var question: String
): Serializable