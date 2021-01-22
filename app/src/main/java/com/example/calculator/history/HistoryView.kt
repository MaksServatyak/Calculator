package com.example.calculator.history

import com.example.calculator.HistoryListAction

interface HistoryView {

    interface View {
        fun showResult(result: String?)
    }

    interface Presenter{
        fun getResult(history: HistoryListAction?)
    }
}