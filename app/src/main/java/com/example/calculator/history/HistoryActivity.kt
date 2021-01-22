package com.example.calculator.history

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator.HistoryListAction
import com.example.calculator.calculator.MainActivity
import com.example.calculator.R
import kotlinx.android.synthetic.main.activity_history.*


@Suppress("PLUGIN_WARNING")
class HistoryActivity : AppCompatActivity(), HistoryView.View {

    private lateinit var presenter: HistoryView.Presenter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        presenter = HistoryPresenter(this)

        val historyClass = intent.extras?.getParcelable<HistoryListAction>("history")
        presenter.getResult(historyClass)


        btn_clear.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun showResult(result: String?) {
        history_text.text = result
    }

}

