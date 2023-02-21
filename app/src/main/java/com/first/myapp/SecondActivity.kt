package com.first.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var userName:String
    private lateinit var age:String
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val weightText = findViewById<EditText>(R.id.etweight)
        val heightText = findViewById<EditText>(R.id.etheight)
        val calcButton = findViewById<Button>(R.id.btncalculate)
        userName = intent.getStringExtra("User").toString()
        age =intent.getStringExtra("userAge").toString()
        val moreInfo= findViewById<TextView>(R.id.tvmoreinfo)
        calcButton.setOnClickListener{

            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (weight==""){
                Toast.makeText(this,"please Enter your weight",Toast.LENGTH_SHORT).show()
            }
            else if (height==""){
                Toast.makeText(this,"please Enter your height",Toast.LENGTH_SHORT).show()
            }
            else {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2dg = String.format("%.2f", bmi).toFloat()
                resultDisplayer(bmi2dg)
            }
        }


    }

    private fun resultDisplayer(bmi:Float){
        val tvIndex = findViewById<TextView>(R.id.tvindex)
        val tvStatus =findViewById<TextView>(R.id.tvstatus)
        val tvMoreInfo = findViewById<TextView>(R.id.tvmoreinfo)

        tvIndex.text=bmi.toString()
        var message =""
        var status =""
        var color=0

        when {
            bmi<18.5->{
                status="You are UnderWeight"
                color = R.color.Under_weight
                message = "$userName the norma range is 18.5 - 24.9  but at the age of " +
                        "$age you are underweight so increase your diet"

        }
            bmi in 18.5..24.99->{
                status="You are healthy"
                color = R.color.normal_weight
                message = "$userName the norma range is 18.5 - 24.9  but at the age of " +
                        "$age you are healty stay normal"


            }
            bmi in 25.00..29.99->{
                status="You are overWight"
                color = R.color.Over_weight
                message = "$userName the norma range is 18.5 - 24.9  but at the age of " +
                        "$age you are overwight do physical exercise"

            }
            bmi >29.99->{
                status="You are obese"
                color = R.color.Over_weight
                message = "$userName the norma range is 18.5 - 24.9  but at the age of " +
                        "$age you are obese see a doctor"

            }

        }
        tvStatus.setTextColor(ContextCompat.getColor(this,color))
        tvStatus.text=status
        tvMoreInfo.text=message

    }
}