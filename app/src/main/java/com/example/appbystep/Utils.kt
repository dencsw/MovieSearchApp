package com.example.appbystep
import android.util.Log
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

var movieList: ArrayList<FilmModel> = ArrayList<FilmModel>()
fun getRequest():ArrayList<FilmModel> {
    try{
        val retrofit = Retrofit.Builder()
            .baseUrl(BasicSettings.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create Service
        val service = retrofit.create(KinoAPI::class.java)
        CoroutineScope(Dispatchers.IO).launch {


            val top_type = "TOP_100_POPULAR_FILMS"
            val page = 1
            // Do the GET request and get response
            val response = service.getFilms(top_type, page)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    val movies: FilmListData? = response.body()
                    if (movies != null) {
                        for (m in movies.films) {
                            movieList.add(m)
                        }
                    } else {
                        println("i'm here")
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }

            }
        }


        return movieList
    }catch(e:Exception) {
        return movieList
    }
}


fun createRequest(page:Int = 1 )= Observable.create<String> {

    val url = BasicSettings.BASE_URL+"top?type=${BasicSettings.TYPE}&page=$page"

    val urlConnection =

        URL(url).openConnection()  as HttpURLConnection
    try {
        urlConnection.setRequestProperty("X-API-KEY",BasicSettings.TOKEN)
        urlConnection.connect() // само обращение в сеть

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK) // проверка результата, что он 200
            it.onError(RuntimeException(urlConnection.responseMessage))
        else {
            println("IM HERE IDIOT")
            val str = urlConnection.inputStream.bufferedReader().readText() // читаем urlConnection как текст
            it.onNext(str)
        }
    } finally {
        urlConnection.disconnect()
    }
}
