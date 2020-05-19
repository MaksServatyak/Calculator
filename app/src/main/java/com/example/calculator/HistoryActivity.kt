package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder


@Suppress("PLUGIN_WARNING")
class HistoryActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val historyClassAction = intent.extras?.getParcelable<HistoryListAction>("historyAction")
        val historyClassAnswer = intent.extras?.getParcelable<HistoryListAnswer>("historyAnswer")
        val historylistAnswer = historyClassAnswer?.listAnswer
        val historylistAction = historyClassAction?.listAction
        val text: MutableList<String> = mutableListOf()
        for (i in 0 until (historylistAnswer?.size ?: 0)) {
            text.add("${historylistAction?.get(i).toString()}=${historylistAnswer?.get(i).toString()}")
        }
        history_text.text = historylistAction?.let { historylistAnswer?.let { it1 -> concatResults(it, it1) } }
        btn_clear.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun concatResults(actions: MutableList<String>, answers: MutableList<String>): String {
        val resultStringBuilder = StringBuilder()
        for (i in 0 until actions.size) {
            resultStringBuilder.append(actions[i]).append("=").append(answers[i]).append("\n")
        }
        return resultStringBuilder.toString()
    }

}

