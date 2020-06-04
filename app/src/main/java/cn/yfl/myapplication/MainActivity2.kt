package cn.yfl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_title_custom_bar.*


class MainActivity2 : AppCompatActivity() {
    companion object {
        val TITLE = "title"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val stringExtra = intent?.getStringExtra(TITLE)
        tv_custom_title.text=stringExtra
    }
}