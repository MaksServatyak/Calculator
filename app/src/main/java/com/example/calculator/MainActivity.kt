package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity() {

    private val historyAction: MutableList<String> = mutableListOf()
    private val historyAnswer: MutableList<String> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }
        btn_divide.setOnClickListener { setTextFields("/") }
        btn_minus.setOnClickListener { setTextFields("-") }
        btn_plus.setOnClickListener { setTextFields("+") }
        btn_multiply.setOnClickListener { setTextFields("*") }
        btn_0.setOnClickListener { setTextFields("0") }
        btn_point.setOnClickListener { setTextFields(".") }
        btn_History.setOnClickListener {
            showHistory()
        }

        btn_equal.setOnClickListener {

            historyAction.add(math_operation.text.toString())

            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if (result == longRes.toDouble()) {
                    result_text.text = longRes.toString()
                    historyAnswer.add(longRes.toString())
                } else {
                    result_text.text = result.toString()
                    historyAnswer.add(result.toString())

                }
            } catch (e: Exception) {
                Log.d("Помилка", "повідомлення:${e.message}")
            }
        }
        btn_delete.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }


    }


    private fun setTextFields(str: String) {
        if (result_text.text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }
        math_operation.append(str)
    }

    private fun showHistory() {

        val intent = Intent(applicationContext, HistoryActivity::class.java)
        intent.putExtra("history", HistoryListAction(historyAction, historyAnswer))
        startActivity(intent)
    }
}
