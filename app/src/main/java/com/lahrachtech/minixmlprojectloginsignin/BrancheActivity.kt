package com.lahrachtech.minixmlprojectloginsignin

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import com.lahrachtech.textinputlayout.Branche
import kotlinx.android.synthetic.main.activity_branche.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_branche.view.*

class BrancheActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference;
    lateinit var nameOfTheBranches: ArrayList<String>
    var years= arrayOf("First year","second year")
    //add branches to drop down manu

    override fun onStart() {
        super.onStart()
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nameOfTheBranches.clear()
                for (item in snapshot.children) {
                    val getBranche = item.getValue(Branche::class.java)
                    nameOfTheBranches.add ( 0,getBranche!!.brancheName)
                }
                val adapter = ArrayAdapter(this@BrancheActivity, android.R.layout.simple_dropdown_item_1line, nameOfTheBranches)
                chooseBranch.setAdapter(adapter)

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BrancheActivity, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //add branches to firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branche)
        mDatabase = FirebaseDatabase.getInstance().getReference("branches");
        nameOfTheBranches = arrayListOf()
        val listItems = arrayOf(
            "First Year",
            "Last Year"
        )
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Choose Year")
            .setSingleChoiceItems(
                listItems,
                0
            ) { _, i ->
                Toast.makeText(
                    this,
                    "${listItems[i]}",
                    Toast.LENGTH_SHORT
                ).show()
            }.setPositiveButton("accept") { _, _ ->

                Toast.makeText(
                    this,
                    "accept",
                    Toast.LENGTH_SHORT

                ).show()
            }.setNegativeButton("decline") { _, _ ->
                Toast.makeText(
                    this,
                    "decline",
                    Toast.LENGTH_SHORT

                ).show()
            }.create()
        chooseBranch.setOnItemClickListener { parent, view, position, id ->
            dialog.show()
//            val selected = parent.getItemAtPosition(position) as String
//            // Do something with the selected item
//            Toast.makeText(this, "Selected: $selected", Toast.LENGTH_SHORT).show()
        }
        // add branches
        faBtnAddBranch.setOnClickListener {
            val (view, alertDialog) = alertBuilder(R.layout.dialog_branche)
            alertDialog.show()
            view.btnAddNote.setOnClickListener {
                val content = view.etBrancheName.text.toString()
                if (content.isNotEmpty()) {
                    val id = mDatabase.push().key
                    val branche = Branche(id!!, content, content.acronym())
                    mDatabase.child(id).setValue(branche)
                    nameOfTheBranches.add(branche.brancheName)

                    alertDialog.dismiss()
                } else {
                    Toast.makeText(this, "errrooooorrrr", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun alertBuilder(srcLayout: Int): Pair<android.view.View, AlertDialog> {
        val alertBuilder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(srcLayout, null)
        alertBuilder.setView(view)
        val alertDialog = alertBuilder.create()
        return Pair(view, alertDialog)
    }

    fun String.acronym(): String {
        var acronym = ""
        val words = this.trim().split("\\s+".toRegex())
        for (word in words) {
            val firstCapitalLetter = word.trim().first().uppercaseChar()
            acronym += firstCapitalLetter
        }
        return acronym
    }

}