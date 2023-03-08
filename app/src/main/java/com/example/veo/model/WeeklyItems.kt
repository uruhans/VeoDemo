package com.example.veo.model

import com.google.gson.annotations.SerializedName

data class WeeklyItems (
    @SerializedName("created_by"     ) var createdBy     : String?          = null,
    @SerializedName("description"    ) var description   : String?          = null,
    @SerializedName("favorite_count" ) var favoriteCount : Int?             = null,
    @SerializedName("id"             ) var id            : String?          = null,
    @SerializedName("items"          ) var items         : ArrayList<TmDbItem> = arrayListOf(),
    @SerializedName("item_count"     ) var itemCount     : Int?             = null,
    @SerializedName("iso_639_1"      ) var iso6391       : String?          = null,
    @SerializedName("name"           ) var name          : String?          = null,
    @SerializedName("poster_path"    ) var posterPath    : String?          = null
)