package randombgappkotlinandroid.gohool.com.thesimplelistview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var personList: ArrayList<Person>

        var person1 =  Person("Jim", 45)
        var person =  Person("James", 145)

        personList = ArrayList()

        personList.add(person)
        personList.add(person1)





        //Listviews = adapter and datasource
        var namesArray: Array<String> = arrayOf("Jenny", "Paul", "Santos", "Lena", "Gina",
                "Santos", "Lee", "Riujy", "Hiroki", "Jane", "Gloria", "Denise", "Chuck",
                "Melissa", "Bonni", "Arao", "Ines")

        var namesAdapter: ArrayAdapter<String> = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, namesArray)

        listview.adapter = namesAdapter

        listview.setOnItemClickListener { parent, view, position, id ->

            var itemName: String = listview.getItemAtPosition(position).toString()
             Toast.makeText(this, "Id: $itemName", Toast.LENGTH_LONG).show()
        }





    }
}
