package com.sweta.gdscsampleapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sweta.gdscsampleapp.R
import com.sweta.gdscsampleapp.model.Student
import com.sweta.gdscsampleapp.utils.StudentDataStore

/**
 * Launcher Activity
 */
class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName

    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById(R.id.et_name) as EditText
        val etAge = findViewById(R.id.et_age) as EditText
        val etClgName = findViewById(R.id.et_clg_name) as EditText
        val etHighestDegree = findViewById(R.id.et_highest_degree) as EditText

        val btnReviewDetails = findViewById(R.id.btn_review_details) as Button

        btnReviewDetails.setOnClickListener {
            val validationState = checkValidationState(etAge = etAge.text.toString(), etName = etName.text.toString(), etClgName = etClgName.text.toString(), etHighestDegree = etHighestDegree.text.toString())

            //val validationState = checkValidationState(etName = etName.text.toString(), etAge = etAge.text.toString(), etHighestDegree = etHighestDegree.text.toString())

            if (StudentDataStore.ValidationState.VALID == validationState) {
                student = Student(
                    etName.text.toString(),
                    etAge.text.toString().toInt(),
                    etClgName.text.toString(),
                    etHighestDegree.text.toString()
                )

                Log.i(LOG_TAG, "The added student details are: $student")

                val reviewDetailsIntent: Intent =
                    Intent(this, ReviewStudentDetailsActivity::class.java).apply {
                        putExtra(StudentDataStore.INTENT_EXTRA_STUDENT, student)
                    }
                startActivity(reviewDetailsIntent)
            }
            else {
                showError(validationState)
                //showError()
            }
        }
    }

    private fun showError(validationState: StudentDataStore.ValidationState = StudentDataStore.ValidationState.VALID)
    {
        val errorMessage = when (validationState)
        {
            StudentDataStore.ValidationState.INVALID_NAME -> resources.getString(R.string.enter_valid_name)
            StudentDataStore.ValidationState.INVALID_AGE -> "Enter valid age"
            StudentDataStore.ValidationState.INVALID_CLG -> "Enter valid college name"
            StudentDataStore.ValidationState.INVALID_DEGREE -> "Enter valid degree"
            else -> {
                "Unknown Error"
            }
        }

        Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun checkValidationState(
        etName: String?,
        etAge: String?,
        etClgName: String?,
        etHighestDegree: String?
    ): StudentDataStore.ValidationState {
        return if (TextUtils.isEmpty(etName)) {
                StudentDataStore.ValidationState.INVALID_NAME
            }
        else if (TextUtils.isEmpty(etAge)) {
            StudentDataStore.ValidationState.INVALID_AGE
            }
        else if (TextUtils.isEmpty(etClgName)) {
            StudentDataStore.ValidationState.INVALID_CLG
        }
        else if (TextUtils.isEmpty(etHighestDegree)) {
            StudentDataStore.ValidationState.INVALID_DEGREE
        }
        else {
            StudentDataStore.ValidationState.VALID
        }
    }
}