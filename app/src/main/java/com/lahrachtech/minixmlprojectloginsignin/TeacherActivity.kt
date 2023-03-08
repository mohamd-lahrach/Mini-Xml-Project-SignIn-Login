package com.lahrachtech.minixmlprojectloginsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_teacher.*

class TeacherActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference;
    lateinit var nameOfTheBranches: ArrayList<String>
    override fun onStart() {
        super.onStart()
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nameOfTheBranches.clear()
                for (item in snapshot.children) {
                    val getBranche = item.getValue(Branche::class.java)
                    nameOfTheBranches.add(0, getBranche!!.brancheName)
                }
                val adapter = ArrayAdapter(
                    this@TeacherActivity,
                    android.R.layout.simple_dropdown_item_1line,
                    nameOfTheBranches
                )
                chooseBrancheForTeacher.setAdapter(adapter)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@TeacherActivity, "error", Toast.LENGTH_SHORT).show()
            }
        })
        getBranchByName { brnch ->
            if (brnch != null) {
                Toast.makeText(this@TeacherActivity, "${brnch.toString()}", Toast.LENGTH_SHORT)
                    .show()

                addTeacher.setOnClickListener {
                    val firstName = etFirstName.text.toString()
                    val lastName = etLastName.text.toString()
                    val age = etAge.text.toString()
                    val cin = etCIN.text.toString()
                    val email = etEmail.text.toString()
                    val phone = etPhone.text.toString()
                    if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty() && cin.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()) {
                        val teacher = Teacher(firstName, lastName, age, cin, email, phone)
                        brnch.addTeacher(teacher)
                    } else {
                        Toast.makeText(this, "errrooooorrrr", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this@TeacherActivity, "tyrftyfuy", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        mDatabase = FirebaseDatabase.getInstance().getReference("branches");
        nameOfTheBranches = arrayListOf()


    }

    fun getBranchByName(callback: (Branche?) -> Unit) {
        chooseBrancheForTeacher.setOnItemClickListener { parent, view, position, id ->
            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var brnch: Branche? = null
                    for (item in snapshot.children) {
                        val getBranche = item.getValue(Branche::class.java)
                        if (getBranche!!.brancheName == nameOfTheBranches[position]) {
                            brnch = getBranche
                        }
                    }
                    callback(brnch)
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@TeacherActivity, "erooooooooor", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}