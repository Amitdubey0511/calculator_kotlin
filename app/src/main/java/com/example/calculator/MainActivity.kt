package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    var lastnumber : Boolean = true
    var lastdot : Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        tvInput =findViewById(R.id.tvinput)
    }

    fun ondigit(view:View) {
        tvInput?.append((view as Button).text)
        lastdot=false
        lastnumber=true
    }
    fun onclear(view: View){
        tvInput?.text=""
    }
    fun ondecimal(view: View){
        if(lastnumber&& !lastdot){
            tvInput?.append(".")
            lastdot=true
            lastnumber=false
        }
    }
    fun onoperator(view: View){
        tvInput?.text?.let {
            if(lastnumber && !isoperatoradded(it.toString())){
                tvInput?.append((view as Button).text)
                lastdot=false
                lastnumber= false

            }
        }
    }
    fun onEqual(view: View){
        if(lastnumber){
            var  tvvalue= tvInput?.text.toString()
            var prefix=""
            try {
                if(tvvalue.startsWith("-")){
                    prefix="-"
                    tvvalue=tvvalue.substring(1)
                }
                if(tvvalue.contains("-")){
                    val splitvalue =tvvalue.split("-")
                    var one =splitvalue[0]
                    var two =splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvInput?.text =(one.toDouble() - two.toDouble()).toString()
                }else if(tvvalue.contains("+")){
                    val splitvalue =tvvalue.split("+")
                    var one =splitvalue[0]
                    var two =splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvInput?.text =(one.toDouble() + two.toDouble()).toString()
                }else if(tvvalue.contains("×")){
                    val splitvalue =tvvalue.split("×")
                    var one =splitvalue[0]
                    var two =splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvInput?.text =(one.toDouble() * two.toDouble()).toString()
                }else if(tvvalue.contains("÷")){
                    val splitvalue =tvvalue.split("÷")
                    var one =splitvalue[0]
                    var two =splitvalue[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tvInput?.text =(one.toDouble() / two.toDouble()).toString()
                }


            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun isoperatoradded(value:String) : Boolean {
        if(value.startsWith( "-")){
            return false
        }
        else {
            return value.contains("+")||value.contains("/")||value.contains("*")||value.contains("-")
        }
    }
}