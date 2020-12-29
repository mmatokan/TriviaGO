package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
//import android.support.wearable.activity.WearableActivity
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    var firstName: TextView? = null
    var lastName: TextView? = null
    var userName: TextView? = null
    var email: TextView? = null
    var password: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firstName = findViewById<TextView?>(R.id.nameSignUp)
        lastName = findViewById<TextView?>(R.id.surnameSignUp)
        userName = findViewById(R.id.usernameSignUp)
        email = findViewById(R.id.emailAddress)
        password = findViewById(R.id.passwordSignUp)


        val returnButton = findViewById<ImageButton>(R.id.returnButton)
        returnButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val signUpButtNext = findViewById<Button>(R.id.signUpButton)
        signUpButtNext.setOnClickListener {
            val userFirstName = firstName?.text.toString()
            val userLastName = lastName?.text.toString()
            val userUserName = userName?.text.toString()
            val userEMail = email?.text.toString()
            val userPassword = password?.text.toString()
           if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEMail).matches()) {
                val toastMessage =
                    Toast.makeText(applicationContext, "Invalid e-mail format", Toast.LENGTH_LONG)
                toastMessage.show()

            }
            //postaviti spremanje u user (? povezati sa backendom ?)

        }
    }
}