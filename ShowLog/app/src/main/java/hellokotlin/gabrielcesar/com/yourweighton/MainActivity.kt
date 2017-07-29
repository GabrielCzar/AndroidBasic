package hellokotlin.gabrielcesar.com.yourweighton

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun showLog(v : View) {
        v as CheckBox
        Log.d("Click", "entrou" + v.text)
    }
}
