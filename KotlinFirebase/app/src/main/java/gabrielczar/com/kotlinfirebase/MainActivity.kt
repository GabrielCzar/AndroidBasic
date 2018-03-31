package gabrielczar.com.kotlinfirebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private var mAuth :FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var firebaseDatabase: FirebaseDatabase? = FirebaseDatabase.getInstance()
        //var databaseRef = firebaseDatabase!!.getReference("messages") // Reference node messages
        var databaseRef = firebaseDatabase!!.getReference("messages").push() // reference different keys

        mAuth = FirebaseAuth.getInstance()

        mAuth!!.createUserWithEmailAndPassword("gabriel@me.com", "gabriel")
                .addOnCompleteListener(this, {
                    task ->
                    if (task.isSuccessful) {
                        var user :FirebaseUser = mAuth!!.currentUser!!
                        currentUser = user
                        Log.d("USER", user.email)
                    } else {
                        Log.d("ERROR", task.exception!!.message)
                    }
                })

//        // Sign in
//        mAuth!!.signInWithEmailAndPassword("gabrielcesar.a.l@gmail.com", "gabriel")
//                .addOnCompleteListener{
//                    task ->
//                    if (task.isSuccessful)
//                        // Sign is successful
//                        Toast.makeText(this, "Sign Success!", Toast.LENGTH_LONG).show()
//                    else
//                        Toast.makeText(this, "Sign Unsuccessful", Toast.LENGTH_LONG).show()
//                }

//        // Object set and get
//        var employee = Employee("Czar", homeAddress = "BR", age = 20, position = "Unknown")
//
//        databaseRef.setValue(employee)
//
//        databaseRef.addValueEventListener(object :ValueEventListener {
//            override fun onDataChange(data: DataSnapshot?) {
//                var value = data!!.value as HashMap<String, Any>
//
//                Log.d("SUCCESS", "Message: " + value)
//            }
//
//            override fun onCancelled(error: DatabaseError?) {
//                Log.w("ERROR", "Failed to read value.", error!!.toException())
//            }
//        })


//        // Save String as value
//        databaseRef.setValue("Hello World!")
//
//        // Read from Cloud DB
//        databaseRef.addValueEventListener(object :ValueEventListener {
//            override fun onDataChange(data: DataSnapshot?) {
//                var value : String = data!!.value as String
//                Log.d("SUCCESS", "Message: " + value)
//            }
//
//            override fun onCancelled(error: DatabaseError?) {
//                Log.w("ERROR", "Failed to read value.", error!!.toException())
//            }
//        })
    }



    override fun onStart() {
        super.onStart()
        currentUser = mAuth!!.currentUser

        if (currentUser != null)
            Toast.makeText(this, "User logged!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, "User Doesn't logged!", Toast.LENGTH_LONG).show()

        // call fun to update user interface

    }

    data class Employee(var name :String, var age:Int,
                        var homeAddress :String, var position :String)
}
