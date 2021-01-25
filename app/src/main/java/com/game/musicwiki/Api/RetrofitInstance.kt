package com.rekord.movieapplication.Retrofit

import com.game.musicwiki.Api.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    companion object {

        val baseURL = "http://ws.audioscrobbler.com/"
        val apiKey="bbaf8c3648d0b473753d55896cec06ce"
        val format  ="json"


        val  getTagMethod ="chart.gettoptags"
        val getDetailsMethod = "tag.getinfo"
        val getAlbum = "tag.gettopalbums"
        var getArtist = "tag.gettopartists"
  var getTracks= "tag.gettoptracks"

        var getAlbumInfo= "album.getinfo"
        var getArtistInfo= "artist.getinfo"

        val getArtistTopAlbum="artist.gettopalbums"
        val getArtistToptrack= "artist.gettoptracks"

//        musickwiki
//        API key	bbaf8c3648d0b473753d55896cec06ce
//        Shared secret	046f5db1f5875241455467f3420635d4
//        Registered to	vivek_kujur


    }

    private val api: RetrofitServices

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        api = retrofit.create(RetrofitServices::class.java)
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
        return builder.build()
    }


    fun getTagsApi(): Call<TagsApiResponse> {
        return api.getTagsApi(apikey = apiKey,method =getTagMethod,format = format )
    }
    fun getTagsDetailsApi(tag:String): Call<TagDetailsApiResponse> {
        return api.getTagsDetailsApi(apikey = apiKey,method =getDetailsMethod,format = format, tag= tag )
    }
    fun getAlbumApi(tag:String): Call<AlbumApiResponse> {
        return api.getAlbumApi(apikey = apiKey,method = getAlbum,format = format, tag= tag )
    }

    fun getArtistApi(tag:String): Call<ArtistApiResponse> {
        return api.getArtistApi(apikey = apiKey,method = getArtist,format = format, tag= tag )
    }

    fun getTracksApi(tag:String): Call<TrackApiResponse> {
        return api.getTracksApi(apikey = apiKey,method = getTracks,format = format, tag= tag )
    }

    fun getAlbumDetailsApi(artist:String,album:String): Call<AlbumDetailsApiResponse> {
        return api.getAlbumDetailsApi(apikey = apiKey,method = getAlbumInfo,format = format, artist= artist,  album = album )
    }

    fun getArtistDetailsApi(artist:String): Call<ArtistDetailsApiResponse> {
        return api.getArtistDetailsApi(apikey = apiKey,method = getArtistInfo,format = format, artist= artist )
    }

    fun getArtistTopAlbums(artist:String): Call<ArtistTopAlbumsApiResponse> {
        return api.getArtistTopAlbums(apikey = apiKey,method = getArtistTopAlbum,format = format, artist= artist )
    }

    fun getArtistTopTracks(artist:String): Call<ArtistTopTracksApiResponse> {
        return api.getArtistTopTracks(apikey = apiKey,method = getArtistToptrack,format = format, artist= artist )
    }


}
