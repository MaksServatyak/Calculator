package com.example.calculator.calculator

interface CalculatorView {
    interface View {
        fun showHistory()
        fun updateMathOperationTv(result_text: String)
        fun updateResultTv(text: String)
    }

    interface Presenter {

        fun setTextFields(str: String, result_text: String)


    }

}