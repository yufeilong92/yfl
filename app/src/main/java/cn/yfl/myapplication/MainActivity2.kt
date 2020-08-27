package cn.yfl.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.backpacker.yflLibrary.kotlin.KotlinCountDownTimer
import com.backpacker.yflLibrary.view.dialog.LoadingDialogManager
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.layout_title_custom_bar.*


class MainActivity2 : AppCompatActivity() {
    companion object {
        val TITLE = "title"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_send_code.setOnClickListener {
            KotlinCountDownTimer.startTime(
                this, R.color.text_main_bg,R.color.white, R.color.white, R.color.text_green, btn_send_code
            ) {
                Toast.makeText(this, "结束", Toast.LENGTH_SHORT).show();
            }
        }

    }
}