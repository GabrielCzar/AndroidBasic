package hellokotlin.gabrielcesar.com.showlog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLog (v : View) {
        if (v as CheckBox != null || v as TextView != null){
            Log.ASSERT
            Log.d("CLICK", "Mensage : " + v.text)
        } else
            Log.ERROR
    }
}
