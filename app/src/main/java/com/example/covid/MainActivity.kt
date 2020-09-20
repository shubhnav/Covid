package com.example.covid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.Adapter.Adapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity()  {

    private  val BASE_URL = "https://api.covid19api.com/"
    private lateinit var disposable : CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        httpRequest()
//        val list = ArrayList<Country>()
//        list.add(Country("india","india","it's me"))
//        adapter(list)
        }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun adapter(t:ArrayList<Country>){
        val rvAdapter = findViewById<View>(R.id.my_recycler_view) as RecyclerView
        rvAdapter.layoutManager = LinearLayoutManager(this)
        rvAdapter.adapter = Adapter(t)
    }
    private fun httpRequest(){
            disposable = CompositeDisposable()
            //val gson = GsonBuilder().setLenient().create()
            // instance which uses Interface and builder api
            // allow defining URL endpoints
            val retrofit  =  Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val requestInterface = retrofit.create(CovidService::class.java)

            disposable.add(requestInterface.india().observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).subscribeWith(object :
                DisposableSingleObserver<List<Country>>(){

                override fun onSuccess(t: List<Country>) {
                    for (x in t){
                        x.CountryCode = SimpleDateFormat("E MMM dd HH:mm:ss yyyy").format(x.Date)
                    }
                    adapter(t as ArrayList<Country>)
                }

                override fun onError(e: Throwable) {
                    Log.d("covid - error call",e.message)
                }
            }))
        }
    }






