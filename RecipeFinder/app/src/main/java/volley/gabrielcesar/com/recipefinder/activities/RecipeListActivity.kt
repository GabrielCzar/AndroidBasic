package volley.gabrielcesar.com.recipefinder.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_recipe_list.*
import org.json.JSONException
import org.json.JSONObject
import volley.gabrielcesar.com.recipefinder.R
import volley.gabrielcesar.com.recipefinder.adapter.RecipeListAdapter
import volley.gabrielcesar.com.recipefinder.model.QUERY
import volley.gabrielcesar.com.recipefinder.model.Recipe
import volley.gabrielcesar.com.recipefinder.model.URL

class RecipeListActivity : AppCompatActivity() {
    private var volleyRequest : RequestQueue? = null
    private var recipeList : ArrayList<Recipe>? = null
    private var recipeAdapter : RecipeListAdapter? = null
    private var layoutManager : RecyclerView.LayoutManager? = null

    // private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        var recipeURL : String?
        //progressBar = ProgressBar(this)
        
        var extras = intent.extras
        var ingredients : String = extras.get("ingredients").toString()
        var search : String = extras.get("search").toString()

        if (!ingredients.equals("") && !search.equals(""))
            recipeURL = URL + ingredients + QUERY + search
        else
            recipeURL = "http://recipepuppy.com/api/?i=onions,gerlic&q=omelet&p=3"

        volleyRequest = Volley.newRequestQueue(this)

        recipeList = ArrayList<Recipe>()

        getRecipe(recipeURL)

    }

    fun getRecipe(url : String) {
        val recipeRequest = JsonObjectRequest(Request.Method.GET, url,
                Response.Listener {
                    response: JSONObject ->
                    try {
                        val resultArray = response.getJSONArray("results")
                        for (i in 0..resultArray.length() - 1) {
                            var recipeObj = resultArray.getJSONObject(i)
                            var title = recipeObj.getString("title")
                            var thumbnail = recipeObj.getString("thumbnail")
                            var link = recipeObj.getString("href")
                            var ingredients = recipeObj.getString("ingredients")

                            var recipe : Recipe = Recipe(title, link, ingredients, thumbnail)

                            recipeList!!.add(recipe)

                            recipeAdapter = RecipeListAdapter(recipeList!!, this)
                            layoutManager = LinearLayoutManager(this)

                            recyclerView.layoutManager = layoutManager
                            recyclerView.adapter = recipeAdapter
                        }
                        recipeAdapter!!.notifyDataSetChanged()

                    } catch (e : JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    error : VolleyError? ->
                    try {
                        Log.d("Error", error.toString())
                    } catch (e : JSONException) {
                        e.printStackTrace()
                    }
                })
        volleyRequest!!.add(recipeRequest)
    }
}
