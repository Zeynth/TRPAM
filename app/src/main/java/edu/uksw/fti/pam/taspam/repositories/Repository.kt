package edu.uksw.fti.pam.taspam.repositories

import edu.uksw.fti.pam.taspam.model.Model
import edu.uksw.fti.pam.taspam.model.PopularModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FilmRepository {

    @GET("Jateng/Jateng")
    suspend fun getJateng():List<Model>

    @GET("Sumbar/Sumbar")
    suspend fun getSumbar():List<Model>

//    @GET("Trend")
//    suspend fun getTrendAnime():List<AnimeTrendModel>

    @GET("NTB/NTB")
    suspend fun getNTB(): List<Model>

    companion object {
        var _apiClient: FilmRepository? = null

        fun getClient(): FilmRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/Zeynth/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FilmRepository::class.java)
            }

            return _apiClient!!
        }
    }

}