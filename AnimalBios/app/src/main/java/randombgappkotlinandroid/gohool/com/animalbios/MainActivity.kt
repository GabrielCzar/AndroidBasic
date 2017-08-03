package randombgappkotlinandroid.gohool.com.animalbios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id) {

            cheetahId.id -> {

                var cheetahIntent = Intent(this, DetailsActivity::class.java)
                cheetahIntent.putExtra("animal", "cheetah")

                startActivity(cheetahIntent)

                //Toast.makeText(this, "Cheetah", Toast.LENGTH_LONG).show()
            }
            lionId.id -> {
                //Toast.makeText(this, "Lion", Toast.LENGTH_LONG).show()
                var lionIntent = Intent(this, DetailsActivity::class.java)
                lionIntent.putExtra("animal", "lion")
                startActivity(lionIntent)

            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cheetah = cheetahId
        var lion = lionId


        //register our views to receive onClickListener events
        cheetah.setOnClickListener(this)
        lion.setOnClickListener(this)


    }
}
