package com.sweta.gdscsampleapp.model

import java.io.Serializable

data class Student(
    val name: String = "",
    val age: Int = 0,
    val collegeName: String,
    val highestDegree: String
) : Serializable
