package cn.yfl.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title_bar.lifelistener = {
            Toast.makeText(this@MainActivity, "返回", Toast.LENGTH_SHORT).show();
        }

        title_bar.ivRightlistener = {
            Toast.makeText(this@MainActivity, "右侧", Toast.LENGTH_SHORT).show();
        }
        title_bar.tvRightlistener = {
            Toast.makeText(this@MainActivity, "tv右侧", Toast.LENGTH_SHORT).show();
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra(MainActivity2.TITLE, "mainactivity2")
            startActivity(intent)
        }

    }
}
