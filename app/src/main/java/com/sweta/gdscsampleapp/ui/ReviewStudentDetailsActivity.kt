package com.sweta.gdscsampleapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.sweta.gdscsampleapp.R
import com.sweta.gdscsampleapp.model.Student
import com.sweta.gdscsampleapp.utils.StudentDataStore

/**
 * Activity to review the details to be added and then add
 */
class ReviewStudentDetailsActivity : AppCompatActivity() {
    companion object {
        private val LOG_TAG = ReviewStudentDetailsActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_student_details)

        val student = intent?.getSerializableExtra(StudentDataStore.INTENT_EXTRA_STUDENT) as? Student

        val tvName = findViewById(R.id.tv_review_name) as TextView
        val tvAge = findViewById(R.id.tv_review_age) as TextView
        val tvClg = findViewById(R.id.tv_review_clg) as TextView
        val tvHighestDegree = findViewById(R.id.tv_review_degree) as TextView
        val btnAddStudent = findViewById(R.id.btn_add_student) as Button

        student.let {
            tvName.text = String.format(resources.getString(R.string.name), student?.name)
            tvAge.text = String.format(resources.getString(R.string.age), student?.age)
            tvClg.text = String.format(resources.getString(R.string.college), student?.collegeName)
            tvHighestDegree.text = String.format(
                resources.getString(R.string.review_highest_degree),
                student?.highestDegree
            )
        }

        btnAddStudent.setOnClickListener {
            student?.let {
                StudentDataStore.studentList.add(student)
                Log.i(LOG_TAG, "The added student details are: $student")

                StudentDataStore.studentList.add(student.copy(name = "Jack"))
                Log.i(LOG_TAG, "The copied student details are: ${StudentDataStore.studentList.last()}")

                Log.i(LOG_TAG, "Total number of students are: ${StudentDataStore.studentList.size}")

                val toastMessage = "Total number of students are: ${StudentDataStore.studentList.size}"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()

                val studentListAbove18 = StudentDataStore.studentList.filter { it.age >= 18 }
                Log.i(LOG_TAG, "Total number of students above 18 are: ${studentListAbove18.size}")
            }
        }
    }
}