package hellokotlin.gabrielcesar.com.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var enteredText = enterNameEdt.text

        showNameBtn.setOnClickListener {
            resultView.text = enteredText
        }
    }
}
