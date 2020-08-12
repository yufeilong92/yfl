package cn.yfl.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.backpacker.yflLibrary.view.dialog.LoadingDialogManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getInstance = LoadingDialogManager.get_Instance()
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

        btn_show.setOnClickListener {
            getInstance.showLoading(this)
        }
        btn_showone.setOnClickListener {
            getInstance.showLoading(this, "加载更多...")
        }
        btncannler.setOnClickListener {
            getInstance.dismissLoading()
        }

        val list = mutableListOf<String>()
        for (index in 0..20) {
            list.add("$index")
        }
        val adapter = MainAdapter(this, list)
        val gl = GridLayoutManager(this, 1)
        rlv_content.layoutManager=gl
        rlv_content.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
        rlv_content.adapter = adapter
    }
}
