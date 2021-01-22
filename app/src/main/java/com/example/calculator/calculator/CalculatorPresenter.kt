package com.example.calculator.calculator

import kotlinx.android.synthetic.main.activity_main.*


class CalculatorPresenter(private val view: CalculatorView.View):CalculatorView.Presenter{
    override fun setTextFields(str: String, result_text: String) {

        if (str != "") {
            view.updateMathOperationTv(str)

            view.updateResultTv(str)
        }

    }


}