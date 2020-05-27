package com.proba.telegram3.models

/* Модель User*/

data class User(
    val id:String = "",
    var username:String = "",
    var bio:String = "",
    var fullname:String = "",
    var state:String = "",
    var phone:String = "",
    var photoUrl:String = "empty"
)