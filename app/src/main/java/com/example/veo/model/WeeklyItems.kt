package com.example.veo.model

import com.google.gson.annotations.SerializedName

//Generated from https://json2kt.com/
data class WeeklyItems (
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<TmDbItem> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)