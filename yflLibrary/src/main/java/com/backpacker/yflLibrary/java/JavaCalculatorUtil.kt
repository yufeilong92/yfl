package com.backpacker.yflLibrary.java

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.backpacker.yflLibrary.kotlin.KotlinStringUtil
import com.example.UtilsLibrary.R

/**
 * @Author : YFL  is Creating a porject in basehttps
 * @Package com.backpacker.yflLibrary.java
 * @Email : yufeilong92@163.com
 * @Time :2020/4/9 21:00
 * @Purpose :计算器
 */
object  JavaCalculatorUtil {

    /***
     * @param mContext
     * 显示计算机
     * @return
     */
    fun showCalc(mContext: Context, canable: Boolean) {
        val dialogBuilde = AlertDialog.Builder(mContext)
        val view = LayoutInflater.from(mContext).inflate(R.layout.dialog_calc, null)
        var num1 = 0.0
        var num2 = 0.0
        var Result = 0.0 //计算结果
        var op = 0 //判断操作数，
        var isClickEqu = false //判断是否按了“=”按钮
        var data = ""

        val btnBackspace = view.findViewById<Button>(R.id.btnBackspace)
        val btnClose = view.findViewById<Button>(R.id.btn_clcose)
        val btn7 = view.findViewById<Button>(R.id.btn7)
        val btn8 = view.findViewById<Button>(R.id.btn8)
        val btn9 = view.findViewById<Button>(R.id.btn9)
        val btnDiv = view.findViewById<Button>(R.id.btnDiv)
        val btn4 = view.findViewById<Button>(R.id.btn4)
        val btn5 = view.findViewById<Button>(R.id.btn5)
        val btn6 = view.findViewById<Button>(R.id.btn6)
        val btnMul = view.findViewById<Button>(R.id.btnMul)
        val btn1 = view.findViewById<Button>(R.id.btn1)
        val btn2 = view.findViewById<Button>(R.id.btn2)
        val btn3 = view.findViewById<Button>(R.id.btn3)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val btn0 = view.findViewById<Button>(R.id.btn0)
        val btnC = view.findViewById<Button>(R.id.btnC)
        val btnCE = view.findViewById<Button>(R.id.btnCE)
        val btnEqu = view.findViewById<Button>(R.id.btnEqu)
        val btnSub = view.findViewById<Button>(R.id.btnSub)
        val tvResult = view.findViewById<TextView>(R.id.tvResult)
        val tv_step = view.findViewById<TextView>(R.id.tv_step)
        btnBackspace.setOnClickListener {
            val myStr = tvResult.text.toString()
            val tvStep = tv_step.text.toString()
            try {
                tvResult.text = myStr.substring(0, myStr.length - 1)
            } catch (e: Exception) {
                tvResult.text = ""
            }
            try {
                tv_step.text = tvStep.substring(0, tvStep.length - 1)
            }catch (e:Exception){
                tv_step.text = ""
            }
        }
        btn0.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "0")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString = tvResult.text.toString()
            myString += "0"
            tvResult.text = myString
        }
        btn1.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "1")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString1 = tvResult.text.toString()
            myString1 += "1"
            tvResult.text = myString1
        }
        btn2.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "2")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString2 = tvResult.text.toString()
            myString2 += "2"
            tvResult.text = myString2
        }
        btn3.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "3")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString3 = tvResult.text.toString()
            myString3 += "3"
            tvResult.text = myString3
        }
        btn4.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "4")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString4 = tvResult.text.toString()
            myString4 += "4"
            tvResult.text = myString4
        }
        btn5.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "5")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString5 = tvResult.text.toString()
            myString5 += "5"
            tvResult.text = myString5
        }
        btn6.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "6")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString6 = tvResult.text.toString()
            myString6 += "6"
            tvResult.text = myString6
        }
        btn7.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "7")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString7 = tvResult.text.toString()
            myString7 += "7"
            tvResult.text = myString7
        }
        btn8.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "8")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString8 = tvResult.text.toString()
            myString8 += "8"
            tvResult.text = myString8
        }
        btn9.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "9")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
                num1 = 0.0
            }
            var myString9 = tvResult.text.toString()
            myString9 += "9"
            tvResult.text = myString9
        }
        btnDiv.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "/")
            tv_step.text = setNewStep(data)
            val myStringDiv = tvResult.text.toString()
            if (KotlinStringUtil.isEmpty(myStringDiv)) {
                return@setOnClickListener
            }
            num1 = java.lang.Double.valueOf(myStringDiv!!)
            tvResult.text = null
            op = 4
            isClickEqu = false
        }
        btnMul.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "*")
            tv_step.text = setNewStep(data)

            val myStringMul = tvResult.text.toString()
            if (KotlinStringUtil.isEmpty(myStringMul)) {
                return@setOnClickListener
            }
            num1 = java.lang.Double.valueOf(myStringMul!!)
            tvResult.text = null
            op = 3
            isClickEqu = false
        }
        btnAdd.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "+")
            tv_step.text = setNewStep(data)

            val myStringAdd = tvResult.text.toString()
            if (KotlinStringUtil.isEmpty(myStringAdd)) {
                return@setOnClickListener
            }
            num1 = java.lang.Double.valueOf(myStringAdd!!)
            tvResult.text = null
            op = 1
            isClickEqu = false
        }
        btnSub.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), "-")
            tv_step.text = setNewStep(data)
            val myStringSub = tvResult.text.toString()
            if (KotlinStringUtil.isEmpty(myStringSub)) {
                return@setOnClickListener
            }
            num1 = java.lang.Double.valueOf(myStringSub!!)
            tvResult.text = null
            op = 2
            isClickEqu = false
        }
        btnC.setOnClickListener {
            data = setStep(KotlinStringUtil.getObjectToStr(tv_step), ".")
            tv_step.text = setNewStep(data)
            if (isClickEqu) {
                tvResult.text = null
                isClickEqu = false
            }
            var myStringC = tvResult.text.toString()
            val indexOf = myStringC.indexOf(".")
            if (indexOf == -1) {//首次加入
                if (KotlinStringUtil.isEmpty(myStringC)) return@setOnClickListener
                myStringC += "."
                tvResult.text = myStringC
            }
        }
        btnCE.setOnClickListener {
            tv_step.text = null
            tvResult.text = null;
            op = 0
            num1=0.0
            num2=0.0
        }
        btnEqu.setOnClickListener {
            tv_step.text = null
            val myStringEqu = tvResult.text.toString()
            if (KotlinStringUtil.isEmpty(myStringEqu)) {
                tvResult.text = "$num1"
                isClickEqu = true
                op = 0
                num1 = 0.0
                return@setOnClickListener
            }
            num2 = java.lang.Double.valueOf(myStringEqu)
            tvResult.text = null
            when (op) {
                0 -> Result = num2
                1 -> Result = JavaArithUtil.add(num1,num2,5)
                2 -> Result = JavaArithUtil.sub(num1,num2,5)
                3 -> Result =
                    JavaArithUtil.mul(num1,num2,5)
                4 -> Result = JavaArithUtil.div(num1,num2,5)
                else -> Result = 0.0
            }
            num1 = 0.0
            op = 0
            tvResult.text = java.lang.String.valueOf(Result)
            isClickEqu = true
        }

        dialogBuilde.setView(view)
        dialogBuilde.setCancelable(canable)
        dialogBuilde.create()
        val show = dialogBuilde.show()
        btnClose.setOnClickListener {
            show.dismiss()
        }
    }

    private fun setStep(com: String, data: String): String {
        var buffer = StringBuilder()
        if (KotlinStringUtil.isEmpty(com)) {
            buffer.append(data)
        } else {
            var split = "$com\n$data".split("\n")
            if (split.size > 3) {
                split = split.subList(split.size - 3, split.size)
            }
            for (index in split.indices) {
                buffer.append(split[index])
                if (index != split.size - 1) {
                    buffer.append("\n")
                }
            }
        }
        return buffer.toString()
    }

    private fun setNewStep(com: String): String {
        var buffer = StringBuilder()
        var split = com.split("\n")
        if (split.size > 3) {
            split = split.subList(split.size - 3, split.size)
        }
        for (index in split.indices) {
            buffer.append(split[index])
        }
        return buffer.toString()
    }
}