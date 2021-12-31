package com.sweta.gdscsampleapp.utils

import com.sweta.gdscsampleapp.model.Student

object StudentDataStore {
    const val INTENT_EXTRA_STUDENT = "INTENT_EXTRA_STUDENT"

    enum class ValidationState {
        INVALID_NAME, INVALID_AGE, INVALID_CLG, INVALID_DEGREE, VALID
    }

    var studentList: MutableList<Student>
    init {
        studentList = ArrayList()
    }
}