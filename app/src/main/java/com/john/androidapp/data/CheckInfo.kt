package com.john.androidapp.data

data class CheckInfo (val title:String, var selected: Boolean = false, var onCheckedChange: (Boolean) -> Unit){
}