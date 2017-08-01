package imageviewappkotlinandroid.gohool.com.imageviewapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var catImage = catImageView
        var dogImage = dogImageView


        catImage.setColorFilter(Color.BLUE)
        dogImage.setColorFilter(Color.CYAN)



        catImage.setOnClickListener {

            catImage.clearColorFilter()
            //catImage.setImageDrawable(application.getDrawable(R.drawable.abc_btn_check_material))
            catImage.setColorFilter(Color.BLUE, PorterDuff.Mode.OVERLAY)
           // catImage.setBackgroundColor(Color.MAGENTA)
        }







//        button.setOnClickListener { textView.text = "Hello" }
//
//        var button = findViewById(R.id.button) as Button
//        button.text = "Show"
//
//        var textview = findViewById(R.id.textView) as TextView
//        button.setOnClickListener(View.OnClickListener {
//
//            textview.text = "Hello"
//
//
//
//        })
    }
}
