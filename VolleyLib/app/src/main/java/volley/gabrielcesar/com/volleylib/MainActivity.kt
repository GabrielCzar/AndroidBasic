package volley.gabrielcesar.com.volleylib

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val stringLink = "http://magadistudio.com/complete-android-developer-course-source-files/string.html"
    val movieLink = "https://netflixroulette.net/api/api.php?director=Quentin%20Tarantino"
    val earthquakeLink = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/1.0_hour.geojson"

    var volleyRequest : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volleyRequest = Volley.newRequestQueue(this)
        //getString(stringLink)
        //getJSONArray(movieLink)
        //getJSONObject(earthquakeLink)
    }
    fun getString (url : String) {
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener {
                    response: String? ->
                        try {
                            Log.d("Response: ", response)
                        } catch (e : JSONException) {
                            e.printStackTrace()
                        }
                },
                Response.ErrorListener {
                    error: VolleyError? ->
                        try {
                            Log.d("Error ", error.toString())
                        } catch (e : JSONException) {
                            e.printStackTrace()
                        }
                })

        volleyRequest!!.add(stringRequest)
    }

    fun getJSONObject (url : String) {
        val jsonObj = JsonObjectRequest(Request.Method.GET, url,
                Response.Listener {
                    response: JSONObject ->
                        try {
                            var metadata = response.getJSONObject("metadata")
                            var other = response.getJSONArray("features")
                            Log.d("TYPE : " , other.toString())
                        } catch (e : JSONException) {
                            e.printStackTrace()
                        }

                },
                Response.ErrorListener {
                    e : VolleyError? ->
                        try {
                            Log.d("Error : ", e.toString())
                        } catch (e : JSONException) {
                            e.printStackTrace()
                        }
                })

        volleyRequest!!.add(jsonObj)
    }

    fun getJSONArray (url: String) {
        val jsonRequest = JsonArrayRequest(Request.Method.GET, url,
                Response.Listener {
                    response : JSONArray ->
                        try {
                            Log.d("Response: ", response.toString())
                            for (i in 0..response.length() - 1) {
                                var movie = response.getJSONObject(i)
                                var show_title = movie.getString("show_title")
                                Log.d("Show Title : ", show_title)
                            }
                        } catch (e : JSONException) {
                            e.printStackTrace()
                        }
                },
                Response.ErrorListener {
                    error : VolleyError? ->
                        try {
                            Log.d("Error : ", error.toString())
                        } catch (e: JSONException ) {
                            e.printStackTrace()
                        }
                })
        volleyRequest!!.add(jsonRequest)
    }
}
