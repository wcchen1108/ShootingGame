package tw.edu.pu.csim.mis2b.wcchen.shootinggame

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var img: ImageView
    lateinit var game:Game
    var flag:Boolean = false
    lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}