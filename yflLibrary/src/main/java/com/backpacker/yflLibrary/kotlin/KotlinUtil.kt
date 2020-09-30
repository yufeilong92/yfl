package com.backpacker.yflLibrary.kotlin

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.regex.Pattern
import java.util.*
import android.text.method.PasswordTransformationMethod
import android.text.method.HideReturnsTransformationMethod
import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import android.text.*
import android.widget.*
import java.io.BufferedReader
import java.io.InputStreamReader
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import com.backpacker.yflLibrary.java.JavaArithUtil
import com.backpacker.yflLibrary.java.JavaStringUtil


/**
 * @Title:  kotlin_androidone
 * @Package com.backpacker.UtilsLibrary.kotlin
 * @Description:    工具类
 * @author: L-BackPacker
 * @date:   2019/3/31 22:23
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2019
 */
object KotlinUtil {
    /**
     * 隐藏键盘
     *
     * @param context context
     * @param view    The currently focused view
     */
    fun hideInputMethod(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }

        val imm = context!!
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm!!.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }

    /**
     * 隐藏键盘
     *
     * @param activity activity
     */
    fun hideInputMethod(activity: Activity) {
        hideInputMethod(activity, activity.currentFocus)
    }

    /**
     * 显示输入键盘
     *
     * @param context context
     * @param view    The currently focused view, which would like to receive soft
     * keyboard input
     */
    fun showInputMethod(context: Context?, view: View?) {
        if (context == null || view == null) {
            return
        }

        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(view, 0)
    }


    /**
     * 判断是否为重复字符串
     *
     * @param str ，需要检查的字符串
     */
    fun isRepeatedStr(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val len = str.length
        if (len <= 1) {
            return false
        } else {
            val firstChar = str[0] // 第一个字长度
            for (i in 1 until len) {
                val nextChar = str[i] // 第i个字长度
                if (firstChar != nextChar) {
                    return false
                }
            }
            return true
        }
    }

    /**
     * 判断是否为纯数字
     *
     * @param str ，需要检查的字符串
     */
    fun isNumbericString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }

        val p = Pattern.compile("^[0-9]+$") // 从开头到结尾必须全部为数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否为纯字母
     *
     * @param str
     * @return
     */
    fun isAlphaBetaString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }

        val p = Pattern.compile("^[a-zA-Z]+$") // 从开头到结尾必须全部为字母或者数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否为纯字母或数字
     *
     * @param str
     * @return
     */
    fun isAlphaBetaNumbericString(str: String): Boolean {
        if (TextUtils.isEmpty(str)) {
            return false
        }
        val p = Pattern.compile("^[a-zA-Z0-9]+$") // 从开头到结尾必须全部为字母或者数�?
        val m = p.matcher(str)

        return m.find()
    }

    /**
     * 判断是否包含中文
     *
     * @param str
     * @return
     */
    private val regEx = "[\u4e00-\u9fa5]"
    private val pat = Pattern.compile(regEx)
    fun isContainsChinese(str: String): Boolean {
        return pat.matcher(str).find()
    }

    /**
     * 模式匹配
     *
     * @param pattern
     * @param input
     * @return
     */
    fun patternMatcher(pattern: String, input: String): Boolean {
        if (TextUtils.isEmpty(pattern) || TextUtils.isEmpty(input)) {
            return false
        }
        val pat = Pattern.compile(pattern)
        val matcher = pat.matcher(input)

        return matcher.find()
    }

    /**
     * 获取随机数
     *
     * @return
     */
    fun getRandom8(): Long {
        val str = StringBuilder() //定义变长字符串
        val random = Random()
        //随机生成数字，并添加到字符串
        for (i in 0..7) {
            str.append(random.nextInt(10))
        }
        //将字符串转换为数字并输出
        return Integer.parseInt(str.toString()).toLong()
    }

    /**
     * 获取文件大小
     */
    fun convertFileSizeB(size: Long): String {
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024

        if (size >= gb) {
            return String.format("%.1f GB", size.toFloat() / gb)
        } else if (size >= mb) {
            val f = size.toFloat() / mb
            return String.format(if (f > 100) "%.0f MB" else "%.1f MB", f)
        } else if (size >= kb) {
            val f = size.toFloat() / kb
            return String.format(if (f > 100) "%.0f KB" else "%.1f KB", f)
        } else
            return String.format("%d B", size)
    }

    /**
     * 获取键盘是否弹出
     *
     * @param root
     * @return
     */
    fun getKeybordStatus(root: View): Boolean {
        val screenHeight = IntArray(1)
        val myHeight = IntArray(1)
        val heightDiff = IntArray(1)
        root.viewTreeObserver.addOnGlobalLayoutListener {
            screenHeight[0] = root.rootView.height
            myHeight[0] = root.height
            heightDiff[0] = screenHeight[0] - myHeight[0]
        }
        return heightDiff[0] > 100
    }

    /**
     * 设置背景颜色
     *
     * @param bgAlpha
     */
    fun setBackgroundAlpha(bgAlpha: Float, mContext: Context) {
        val lp = (mContext as Activity).window
            .attributes
        lp.alpha = bgAlpha
        mContext.window.attributes = lp
    }

    fun getArrayString(context: Context, id: Int): Array<String> {
        var lists = context.resources.getStringArray(id)
        return lists
    }

    /**
     * 验证身份证号是否符合规则
     * @param text 身份证号
     * @return
     */
    fun personIdValidation(text: String): Boolean {
        val regx = "[0-9]{17}x"
        val reg1 = "[0-9]{15}"
        val regex = "[0-9]{18}"
        return text.matches(regx.toRegex()) || text.matches(reg1.toRegex()) || text.matches(regex.toRegex())
    }


    fun getFormAssets(context: Context, fileName: String): String? {
        val stringBuilder = StringBuilder()
        BufferedReader(InputStreamReader(context.assets.open(fileName), "UTF-8")).run {
            var line: String? = ""
            do {
                line = readLine()
                if (line != null) {
                    stringBuilder.append(line)
                } else {
                    close()
                    break
                }
            } while (true)
            return stringBuilder.toString()
        }
        return null
    }

    private val MIN_CLICK_DELAY_TIME = 2000
    private var lastClickTime: Long = 0

    /***
     * 处理多次点击问题
     * @return
     */
    fun handleOnDoubleClick(): Boolean {
        val l = System.currentTimeMillis()
        if (l - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = l
            return false
        }
        return true
    }


    private var lastClickTime_ONE: Long = 0

    /***
     * 处理多次点击问题  true 多次 false  不是
     * @return
     */
    fun handleOnDoubleClick_ONE(douTime:Int): Boolean {
        val l = System.currentTimeMillis()
        if (l - lastClickTime_ONE > douTime) {
            lastClickTime_ONE = l
            return false
        }
        return true
    }

    /**
     * 获取本地资源arry数据
     * @param id 名称
     */
    fun getMutablistString(context: Context, id: Int): MutableList<String> {
        val lists = context.resources.getStringArray(id)
        var mutableList = arrayListOf<String>()
        for (item in lists) {
            mutableList.add(item)
        }
        return mutableList
    }

    /**
     * 展示dialog
     * @param dialog
     */
    fun showDialog(dialog: Dialog?) {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
        }
    }

    /**
     * 取消dialog
     * @param dialog
     */
    fun dismissDialog(dialog: Dialog?) {
        if (dialog != null && dialog.isShowing) {
            dialog.dismiss()
        }
    }

    /**
     * 判断字符为空这弹出tos
     */
    fun tShow(mContext: Context, str: String?, con: String): Boolean {
        if (KotlinStringUtil.isEmpty(str)) {
            KotlinT.showToast(mContext, con)
            return true
        }
        return false
    }

    fun etShowOrHine(et: EditText, boolean: Boolean) {
        if (boolean) {
            //选择状态 显示明文--设置为可见的密码
            //mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            /**
             * 第二种
             */
            et.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            //mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            /**
             * 第二种
             */
            et.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }

    fun etShowOrHine(et: EditText, chb: CheckBox) {
        chb.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1) {
                    //选择状态 显示明文--设置为可见的密码
                    et.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    et.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
            }

        })
    }

    fun etShowOrHineTwo(et: EditText, chb: CheckBox) {
        chb.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1) {
                    //选择状态 显示明文--设置为可见的密码
                    //mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    /**
                     * 第二种
                     */
                    et.transformationMethod = HideReturnsTransformationMethod.getInstance();
                } else {
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    //mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    /**
                     * 第二种
                     */
                    et.transformationMethod = PasswordTransformationMethod.getInstance();
                }
            }

        })
    }

    fun copyText(mContext: Context, com: String) {
        val cm = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager;
        // 将文本内容放到系统剪贴板里。
        //        val newPlainText = ClipData.newPlainText("Label", com)
        cm.setPrimaryClip(ClipData.newPlainText("Label", com));
        KotlinT.showToast(mContext, "复制成功")
    }

    /***
     * 银行卡截取获取后四位
     * @param com 截取字段
     * @return
     */
    fun cutOutBank(com: String?): String? {
        if (KotlinStringUtil.isEmpty(com)) {
            return ""
        }
        val length = com!!.length
        if (length <= 4) {
            return com
        }
        val start = length - 4
        val substring = com.substring(start, length)
        return substring
    }


    fun getStarNumer(int: Float): Float {
        val i = JavaArithUtil.divF(int.toDouble(), 20.0, 1)
        return i.toFloat()
    }

    fun getLatLng(string: String): MutableList<String> {
        if (JavaStringUtil.isEmpty(string)) {
            return mutableListOf()
        }
        val list = string.split(",")
        return list as MutableList<String>
    }
    /***
     * @param count 数量
     * @param count 显示
     * @return
     */
    fun showShopBoxNumber(count:Int,tv: TextView){
        tv.text=if (count>=100)"99+" else "$count"
    }
    fun showView(v:View,show:Boolean){
        v.visibility=if (show)View.VISIBLE else View.GONE
    }

    fun showOrInvisibleView(v:View,show:Boolean){
        v.visibility=if (show)View.VISIBLE else View.INVISIBLE
    }

    /**
     * @param k 从右到左开始位置
     * @param num 数据
     * @param number 保留从右到左开始位置后几位数  1位 10 2位100 3位1000
     */
    fun getKNumber(k: Int, num: Int,number:Int): Int {
        //如果k越界，则返回-1
        return if (Math.pow(10.0, k - 1.toDouble()) > num) {
            -1
        } else {
            num / Math.pow(10.0, k - 1.toDouble()).toInt() % number
        }
    }
}