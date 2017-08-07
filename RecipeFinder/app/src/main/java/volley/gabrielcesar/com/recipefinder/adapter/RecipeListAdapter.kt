package volley.gabrielcesar.com.recipefinder.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import volley.gabrielcesar.com.recipefinder.R
import volley.gabrielcesar.com.recipefinder.activities.ShowLinkActivity
import volley.gabrielcesar.com.recipefinder.model.Recipe

/**
 * Created by PC on 05/08/2017.
 */
class RecipeListAdapter(private val list : ArrayList<Recipe>,
                        private val context: Context) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindView(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var thumbnail = itemView.findViewById<ImageView>(R.id.thumbnailView)
        private var title = itemView.findViewById<TextView>(R.id.titleView)
        private var ingredients = itemView.findViewById<TextView>(R.id.ingredientsView)
        private var btnLink = itemView.findViewById<Button>(R.id.btnView)

        fun bindView(recipe : Recipe) {
            title.text = recipe.title
            ingredients.text = recipe.ingredients

            if (!TextUtils.isEmpty(recipe.thumbnail))
                Picasso.with(context)
                        .load(recipe.thumbnail)
                        .placeholder(android.R.drawable.ic_menu_report_image)
                        .error(android.R.drawable.ic_menu_report_image)
                        .into(thumbnail)
            else
                Picasso.with(context).load(R.mipmap.ic_launcher).into(thumbnail)

            btnLink.setOnClickListener {
                var intent = Intent(context, ShowLinkActivity::class.java)
                intent.putExtra("link", recipe.link.toString())
                context.startActivity(intent)
            }
        }

    }
}