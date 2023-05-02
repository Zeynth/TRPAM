package edu.uksw.fti.pam.taspam.model

import com.google.gson.annotations.SerializedName

data class PopularModel(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("bahan")
    val bahan: String,

    @SerializedName("desc")
    val desc: String,

)
