package volley.gabrielcesar.com.recipefinder.model

/**
 * Created by PC on 05/08/2017.
 */
class Recipe (){
    var title : String? = null
    var link : String? = null
    var ingredients : String? = null
    var thumbnail : String? = null

    constructor(title : String, link : String, ingredients : String, thumbnail : String) : this () {
        this.title = title
        this.ingredients = ingredients
        this.thumbnail = thumbnail
        this.link = link
    }
}