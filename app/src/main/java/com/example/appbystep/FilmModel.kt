package com.example.appbystep

data class FilmModel(
    var filmId: Int,
    var nameRu: String,
    var posterUrl: String,
    var posterUrlPreview: String,
    var genres:List<Map<String,String>>,
    var year: String,
    var countries:List<Map<String,String>>,
    var rating:String,
)
