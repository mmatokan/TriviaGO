package hr.fer.ruazosa.networkquiz.entity

import java.io.Serializable
data class SelectableUser (
    var username:String,
    var selected:Boolean
): Serializable