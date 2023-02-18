package com.example.appbystep

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : ComponentActivity() {
//    lateinit var binding: ActivityMainBinding
    var request: Disposable? = null
    lateinit var vRecView:  RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        setContentView(R.layout.activity_main)
        vRecView = findViewById(R.id.act1_recView)
        val o = createRequest()
            .map { Gson().fromJson(it,FilmListData::class.java) }
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        request = o.subscribe({
            showRecView(it.films)
        }, {
            Log.e("test", "", it)
        })
    }


    fun showRecView(filmsList: ArrayList<FilmModel>) {
        vRecView.adapter = RecAdapter(filmsList)
        vRecView.layoutManager = LinearLayoutManager(this)
    }
}


