package com.example.calculator.history

import com.example.calculator.HistoryListAction
import java.lang.StringBuilder

class HistoryPresenter(private val view: HistoryView.View) : HistoryView.Presenter {
    override fun getResult(history: HistoryListAction?) {
        val historyListAnswer = history?.listAnswer
        val historyListAction = history?.listAction

        view.showResult(historyListAction?.let { historyListAnswer?.let { it1 -> concatResults(it, it1) } })
    }

    private fun concatResults(actions: MutableList<String>, answers: MutableList<String>): String {
        val resultStringBuilder = StringBuilder()
        for (i in 0 until actions.size) {
            resultStringBuilder.append(actions[i]).append("=").append(answers[i]).append("\n")
        }
        return resultStringBuilder.toString()
    }
}