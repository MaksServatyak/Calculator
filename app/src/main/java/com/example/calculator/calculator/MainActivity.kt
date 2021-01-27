package com.example.calculator.calculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.calculator.HistoryListAction
import com.example.calculator.R
import com.example.calculator.history.HistoryActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

@Suppress("PLUGIN_WARNING")
class MainActivity : AppCompatActivity(), CalculatorView.View {

    private lateinit var presenter: CalculatorView.Presenter

    private val historyAction: MutableList<String> = mutableListOf()
    private val historyAnswer: MutableList<String> = mutableListOf()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = CalculatorPresenter(this)

        btn_1.setOnClickListener { presenter.setTextFields("1", result_text.text.toString()) }
        btn_2.setOnClickListener { presenter.setTextFields("2", result_text.text.toString()) }
        btn_3.setOnClickListener { presenter.setTextFields("3", result_text.text.toString()) }
        btn_4.setOnClickListener { presenter.setTextFields("4", result_text.text.toString()) }
        btn_5.setOnClickListener { presenter.setTextFields("5", result_text.text.toString()) }
        btn_6.setOnClickListener { presenter.setTextFields("6", result_text.text.toString()) }
        btn_7.setOnClickListener { presenter.setTextFields("7", result_text.text.toString()) }
        btn_8.setOnClickListener { presenter.setTextFields("8", result_text.text.toString()) }
        btn_9.setOnClickListener { presenter.setTextFields("9", result_text.text.toString()) }
        btn_0.setOnClickListener { presenter.setTextFields("0", result_text.text.toString()) }
        btn_divide.setOnClickListener { presenter.setTextFields("/", result_text.text.toString()) }
        btn_minus.setOnClickListener { presenter.setTextFields("-", result_text.text.toString()) }
        btn_plus.setOnClickListener { presenter.setTextFields("+", result_text.text.toString()) }
        btn_multiply.setOnClickListener { presenter.setTextFields("*", result_text.text.toString()) }
        btn_point.setOnClickListener { presenter.setTextFields(".", result_text.text.toString()) }
        btn_History.setOnClickListener {
            showHistory()
        }

        btn_equal.setOnClickListener {

            historyAction.add(math_operation.text.toString())

            try {
//                if (result_text.toString()[(result_text.lastIndex)].equals(0)){
//                    result_text.text="You are stupid"
//                }
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
                result_text.text = "can't be divided by 0"
                historyAnswer.add("error")
                Log.d("Помилка", "повідомлення:${e.message}")
            }
        }
        btn_delete.setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }


    }


    override fun showHistory() {

        val intent = Intent(applicationContext, HistoryActivity::class.java)
        intent.putExtra("history", HistoryListAction(historyAction, historyAnswer))
        startActivity(intent)
    }

    override fun updateMathOperationTv(result_text: String) {
        math_operation.append(result_text)
    }

    override fun updateResultTv(text: String) {
        result_text.text = text
    }
}

