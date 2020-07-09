package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.UtilsLibrary.R
import java.text.NumberFormat
import java.util.*

/**
 * @Author : YFL  is Creating a porject in workerApp_Android
 * @Package cn.ruiye.worker.utils
 * @Email : yufeilong92@163.com
 * @Time :2020/6/29 18:31
 * @Purpose :
 */
object KotlinCalculatorDialog {
    private var text_clear: TextView? = null
    private var text_back: TextView? = null
    private var text_divide: TextView? = null
    private var text_multiply: TextView? = null
    private var text_add: TextView? = null
    private var text_minus: TextView? = null
    private var text_equal: TextView? = null
    private var text_percent: TextView? = null
    private var text_dot: TextView? = null
    private var text_0: TextView? = null
    private var text_1: TextView? = null
    private var text_2: TextView? = null
    private var text_3: TextView? = null
    private var text_4: TextView? = null
    private var text_5: TextView? = null
    private var text_6: TextView? = null
    private var text_7: TextView? = null
    private var text_8: TextView? = null
    private var text_9: TextView? = null
    private var result_text: TextView? = null
    private var scroll_view: ScrollView? = null
    private var history_text: TextView? = null
    private var mContext: Context? = null
    fun showCalculator(mContext: Context) {
        this.mContext = mContext
        val dialogBuilde = AlertDialog.Builder(mContext)
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.dialog_showchanger_infom_new, null)

        text_clear = view.findViewById<TextView>(R.id.text_clear)
        text_clear?.setOnClickListener { onClick(it) }
        text_back = view.findViewById<TextView>(R.id.text_back)
        text_back?.setOnClickListener { onClick(it) }
        text_divide = view.findViewById<TextView>(R.id.text_divide)
        text_divide?.setOnClickListener { onClick(it) }
        text_multiply = view.findViewById<TextView>(R.id.text_multiply)
        text_multiply?.setOnClickListener { onClick(it) }
        text_minus = view.findViewById<TextView>(R.id.text_minus)
        text_minus?.setOnClickListener { onClick(it) }
        text_add = view.findViewById<TextView>(R.id.text_add)
        text_add?.setOnClickListener { onClick(it) }
        text_equal = view.findViewById<TextView>(R.id.text_equal)
        text_equal?.setOnClickListener { onClick(it) }
        text_percent = view.findViewById<TextView>(R.id.text_percent)
        text_percent?.setOnClickListener { onClick(it) }
        text_dot = view.findViewById<TextView>(R.id.text_dot)
        text_dot?.setOnClickListener { onClick(it) }
        result_text = view.findViewById<TextView>(R.id.result_text)
        scroll_view = view.findViewById<ScrollView>(R.id.scroll_view)
        text_0 = view.findViewById<TextView>(R.id.text_0)
        val tv_colose = view.findViewById<TextView>(R.id.tv_colose)
        text_0?.setOnClickListener { onClick(it) }
        text_1 = view.findViewById<TextView>(R.id.text_1)
        text_1?.setOnClickListener { onClick(it) }
        text_2 = view.findViewById<TextView>(R.id.text_2)
        text_2?.setOnClickListener { onClick(it) }
        text_3 = view.findViewById<TextView>(R.id.text_3)
        text_3?.setOnClickListener { onClick(it) }
        text_4 = view.findViewById<TextView>(R.id.text_4)
        text_4?.setOnClickListener { onClick(it) }
        text_5 = view.findViewById<TextView>(R.id.text_5)
        text_5?.setOnClickListener { onClick(it) }
        text_6 = view.findViewById<TextView>(R.id.text_6)
        text_6?.setOnClickListener { onClick(it) }
        text_7 = view.findViewById<TextView>(R.id.text_7)
        text_7?.setOnClickListener { onClick(it) }
        text_8 = view.findViewById<TextView>(R.id.text_8)
        text_8?.setOnClickListener { onClick(it) }
        text_9 = view.findViewById<TextView>(R.id.text_9)
        text_9?.setOnClickListener { onClick(it) }
        history_text = view.findViewById<TextView>(R.id.history_text)
        dialogBuilde.setView(view)
        dialogBuilde.setCancelable(true)
        dialogBuilde.create()
        val dialog = dialogBuilde.show()
        tv_colose.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun setResultWithScroll(result: String) {
        result_text!!.text = result
        scroll_view!!.post {
            scroll_view?.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun onClick(view: View) {
        var shouldAppend = false
        var f = 0 //1 小数点，2 百分号
        var isQUEST = false
        when (view) {
            // Clear button clicked, reset text to "0"
            text_clear -> {
                history_text?.text = ""
                result_text?.text = "0"
            }
            // Backspace button clicked, remove the last char
            text_back -> onBackspaceClick()
            // Equal button clicked, calculate result and display it
            text_equal -> {
                isQUEST = true
                onEqualClick()
            }
            // +-*/ button click
            text_add,
            text_minus,
            text_multiply,
            text_divide -> shouldAppend = onOperatorClick(view as TextView)
            // Percent button clicked
            text_percent -> {//百分号
                f = 2
                shouldAppend = onPercentClick()
            }
            // Dot button clicked
            text_dot -> {//小数点
                f = 1
                shouldAppend = onDotClick()
            }
            // Number(0-9) button clicked
            else -> {
                shouldAppend = true
            }
        }
        if (shouldAppend) {
            val input = (view as TextView).text
            val result = result_text!!.text
            if (isCleared() && input != ".") {
                result_text!!.text = input
            } else {
                setResultWithScroll(result.toString().plus(input))
            }
        }
    }

    private fun onBackspaceClick() {
        if (isCleared()) {
            return
        }
        result_text?.text = with(result_text!!.text) {
            when {
                length == 1 -> "0"
                ',' == get(length - 2) -> substring(0, length - 2)
                else -> substring(0, length - 1)
            }
        }
    }

    private fun onOperatorClick(view: TextView): Boolean {
        if (isCleared() && view.text != "-") {
            return false
        }
        if (result_text?.text.toString().matches(Regex(".*[-+×÷.]"))) {
            return false
        }
        return true
    }

    private fun onPercentClick(): Boolean {
        if (isCleared()) {
            return false
        }
        return result_text!!.text.matches(Regex(".*[0-9]$"))
    }

    private fun onDotClick(): Boolean {
        return result_text!!.text.matches(Regex(".*[0-9]$"))
    }

    private fun isCleared(): Boolean {
        return "0" == result_text!!.text || result_text!!.text.isEmpty()
    }

    private fun onEqualClick() {
        if (isCleared()) {
            return
        }
        if (Regex("[-+×÷.]").matches(result_text!!.text.last().toString())) {
            Toast.makeText(mContext, "符号后请输入数字或者删除!", Toast.LENGTH_SHORT).show()
            return
        }
        history_text!!.text = result_text!!.text.toString().plus("=")
        var expression = result_text!!.text.toString()
            .replace("%", "÷100")
            .replace("-", "+-")
            .replace(",", "").let {
                if (it.startsWith("+")) it.substring(1, it.length) else it
            }
        expression = performMultiplyAndDivide(expression)
        expression = performAdd(expression)
        setResultWithScroll(formatResult(expression))
    }

    private fun performMultiplyAndDivide(expression: String): String {
        return performCalculation(expression, "-?(\\d+)(\\.?\\d*)[×÷]-?(\\d+)(\\.?\\d*)")
    }

    private fun performAdd(expression: String): String {
        return performCalculation(expression, "-?(\\d+)(\\.?\\d*)\\+-?(\\d+)(\\.?\\d*)")
    }

    private fun performCalculation(expression: String, pattern: String): String {
        var newExpression = expression
        var temp = Regex(pattern).find(newExpression) // 3*3 6/2 1+2 48-90
        while (temp != null) {
            newExpression = newExpression.replace(temp.value, performSingleCalculation(temp.value))
            temp = Regex(pattern).find(newExpression)
        }
        return newExpression
    }

    private fun performSingleCalculation(singleExpression: String): String {
        var singleResult = 0.0
        when {
            singleExpression.matches(Regex("-?(\\d+)(\\.?\\d*)\\+-?(\\d+)(\\.?\\d*)")) -> {
                singleResult = singleExpression.let {
                    val numList = it.split("+")
                    numList[0].toDouble() + numList[1].toDouble()
                }
            }
            singleExpression.matches(Regex("-?(\\d+)(\\.?\\d*)×-?(\\d+)(\\.?\\d*)")) -> {
                singleResult = singleExpression.let {
                    val numList = it.split("×")
                    numList[0].toDouble() * numList[1].toDouble()
                }
            }
            singleExpression.matches(Regex("-?(\\d+)(\\.?\\d*)÷-?(\\d+)(\\.?\\d*)")) -> {
                singleResult = singleExpression.let {
                    val numList = it.split("÷")
                    numList[0].toDouble() / numList[1].toDouble()
                }
            }
        }
        return singleResult.toString()
    }

    private fun formatResult(resultString: String): String {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault()).apply {
            maximumFractionDigits = 16 // Same as double
        }
        var toString: String=""
        try {
            toString = numberFormat.format(resultString.toDouble()).toString()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            KotlinT.showToast(mContext!!,"内容输入有误，请修改")
            return resultString
        }
        return toString
    }
}