package volley.gabrielcesar.com.recipefinder.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import volley.gabrielcesar.com.recipefinder.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = Intent(this, RecipeListActivity::class.java)
        var ingredients = ingredientsEdt.text
        var search = searchEdt.text

        intent.putExtra("ingredients", ingredients)
        intent.putExtra("search", search)

        btnGo.setOnClickListener { startActivity(intent) }
    }
}
