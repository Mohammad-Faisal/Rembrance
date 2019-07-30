package com.example.rembrance





data class Word (
    var id: String = "Id",
    var word: String  = "Meaning",
    var meaning: String  ="",
    var synonymes: ArrayList<String>  = ArrayList(),
    var examples: ArrayList<String> = ArrayList(),
    var isArchived: Boolean = false,
    var isFavourite: Boolean = false

)
