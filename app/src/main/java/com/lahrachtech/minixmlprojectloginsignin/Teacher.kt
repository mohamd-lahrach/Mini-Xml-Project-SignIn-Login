package com.lahrachtech.minixmlprojectloginsignin

class Teacher {
    lateinit var id: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var age: String
    lateinit var cin: String
    lateinit var email: String
    lateinit var phoneNumber: String

    companion object {
        private var lastId: Long = 0
        fun nextId(): Long {
            lastId++
            return lastId
        }
    }
        constructor(){
            this.id=nextId().toString()
        }
        constructor(
            _firstName: String,
            _lastName: String,
            _age: String,
            _cin: String,
            _email: String,
            _phoneNumber: String,

            ) {
            this.firstName = _firstName
            this.lastName = _lastName
            this.age = _age
            this.cin = _cin
            this.email = _email
            this.phoneNumber = _phoneNumber
            this.id=nextId().toString()


        }
    }