package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.networkquiz.net.RestFactory
import android.widget.Toast.LENGTH_SHORT
import hr.fer.ruazosa.networkquiz.entity.User


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
            finish()
        }
        val signUpButtNext = findViewById<Button>(R.id.signUpButton)
        signUpButtNext.setOnClickListener {
            val userFirstName: String = firstName?.text.toString()
            val userLastName: String = lastName?.text.toString()
            val userUsername: String = userName?.text.toString()
            val userEmail: String = email?.text.toString()
            val userPassword: String = password?.text.toString()

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                val toastMessage =
                    Toast.makeText(applicationContext, "Invalid e-mail format", Toast.LENGTH_LONG)
                toastMessage.show()

            } else {
                val user = User(
                    userFirstName,
                    userLastName,
                    userUsername,
                    userEmail,
                    userPassword
                )

                RegisterUser().execute(user)

            }
        }
    }

    private inner class RegisterUser: AsyncTask<User, Void, User?>() {

        override fun doInBackground(vararg user: User): User? {
            val rest = RestFactory.instance
            return rest.registerUser(user[0])
        }

        override fun onPostExecute(user: User?) {
            if (user != null) {
                val intent = Intent(this@SignUpActivity, MyProfileActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
            else{
                val toast = Toast.makeText(applicationContext ,"Registration failed!", LENGTH_SHORT)
                toast.show()
            }

        }
    }
}