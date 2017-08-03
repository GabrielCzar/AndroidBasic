package hellokotlin.gabrielcesar.com.navigatinginbetweenactivities

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var data = intent.extras

        if (data != null ) {
            var name = data.get("name")
            var num = data .getInt("num")
            Toast.makeText(this, "Name: " + name.toString() + ", Número: " + num, Toast.LENGTH_LONG).show()
        }

        btn_back.setOnClickListener {
            var returnIntent = this.intent
            returnIntent.putExtra("return", "Hello from Second activity" )
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

}
