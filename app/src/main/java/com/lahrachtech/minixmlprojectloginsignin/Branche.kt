package com.lahrachtech.minixmlprojectloginsignin

import com.google.firebase.database.FirebaseDatabase

class Branche {
    lateinit var id: String
    lateinit var brancheName: String
    lateinit var acronym: String
    lateinit var years: ArrayList<String>
    var teachers: ArrayList<Teacher>

    constructor() {
        this.teachers = arrayListOf()
    }

    constructor(_id: String, _branchName: String, _acronym: String) {
        this.id = _id
        this.brancheName = _branchName
        this.acronym = _acronym
        this.years = arrayListOf("first", "second")
        this.teachers = arrayListOf()

    }

    fun addTeacher(t: Teacher) {
        this.teachers.add(t)
        val branchRef = FirebaseDatabase.getInstance().getReference("branches/$id")
        branchRef.child("teachers").setValue(teachers)
    }

    override fun toString(): String {
        return "Branch(id='$id', branchName='$brancheName', acronym='$acronym')"
    }

}


