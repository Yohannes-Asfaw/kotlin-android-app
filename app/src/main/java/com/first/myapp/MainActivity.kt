package com.first.myapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
  private lateinit  var nameField:EditText
  private lateinit var dob :EditText
  private lateinit var sf:SharedPreferences
  private lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sf =getSharedPreferences("my_sf", MODE_PRIVATE)
        editor=sf.edit()
        val submit = findViewById<Button>(R.id.busubmit)
        val aboutme = findViewById<Button>(R.id.byAboutme)
        nameField =findViewById<EditText>(R.id.editTextPersonName)
        dob =findViewById<EditText>( R.id.editTextNumberDOB)

        var enteredName=""
        var age=""
        submit.setOnClickListener {
         if(nameField.text.toString()==""){
             aboutme.visibility=INVISIBLE
        Toast.makeText(this@MainActivity,
            "please enter your name ",
            Toast.LENGTH_SHORT).show() }
       else if (dob.text.toString() == "") {
             aboutme.visibility=INVISIBLE

             Toast.makeText(this@MainActivity,
                "please enter your dob ",
                Toast.LENGTH_SHORT).show() }

        else {
            val enterdDOB =dob.text.toString().toInt()
             age = (Calendar.getInstance().get(Calendar.YEAR) - enterdDOB).toString()
             enteredName=nameField.text.toString()
            nameField.text.clear()
            dob.text.clear()
            aboutme.visibility=VISIBLE

        }
        aboutme.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("User",enteredName)
            intent.putExtra("userAge",age)

            startActivity(intent)
        }
        }

    }

    override fun onPause() {
        super.onPause()
        val name = nameField.text.toString()
        val dob = dob.text.toString()
        editor.apply {
            putString("sf_name",name)
            putString("sf_dob",dob)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name",null)
        val dob1 =sf.getString("sf_dob",null)
        nameField.setText(name)
        dob.setText(dob1)
    }
}
